package com.example.guardiannet.PatientTracking

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guardiannet.Data.Caretaker
import com.example.guardiannet.Data.Patient

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientDetailsScreen(
    onBackClick: () -> Unit = {},
    onMoreClick: () -> Unit = {},
    onInformCaretakerClick: () -> Unit = {},
    onTrackClick: () -> Unit = {}
) {
    // Hardcoded patient data - replace with actual data later
    val patientDetails = Patient(
        id = "1",
        name = "Yash Karande",
        status = "Out of Safe Zone",
        statusColor = Color(0xFFE53E3E),
        age = "80 years",
        address = "House No. 17, Raghunandan Nagar,\nNear Sinhagad College, Vadgaon\nBudruk,\nPune - 411041, Maharashtra",
        caretaker = Caretaker(
            name = "Rohan Arun Nalawade",
            address = "House No. 17, Raghunandan Nagar,\nNear Sinhagad College, Vadgaon\nBudruk,\nPune - 411041, Maharashtra",
            contact = "8975785013"
        )
    )

    Scaffold(
        topBar = {
            PatientDetailsTopBar(
                onBackClick = onBackClick,
                onMoreClick = onMoreClick
            )
        },
        bottomBar = {
            PatientDetailsBottomBar(
                onInformCaretakerClick = onInformCaretakerClick,
                onTrackClick = onTrackClick
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Patient Profile Section
            PatientProfileSection(patientDetails = patientDetails)

            // Patient Information Section
            PatientInfoSection(patientDetails = patientDetails)

            // Caretaker Details Section
            CaretakerDetailsSection(caretaker = patientDetails)

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// Top Bar Component
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientDetailsTopBar(
    onBackClick: () -> Unit,
    onMoreClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Patient Details",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2C2C2C),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF2C2C2C)
                )
            }
        },
        actions = {
            IconButton(onClick = onMoreClick) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More options",
                    tint = Color(0xFF2C2C2C)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

// Patient Profile Section
@Composable
fun PatientProfileSection(patientDetails: Patient) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Image
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFE1F5FE)),
            contentAlignment = Alignment.Center
        ) {
            // Replace with actual image when available
            // AsyncImage(
            //     model = patientDetails.profileImage,
            //     contentDescription = "${patientDetails.name} profile",
            //     modifier = Modifier.fillMaxSize(),
            //     contentScale = ContentScale.Crop
            // )
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = Color(0xFF0288D1),
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Patient Name
        Text(
            text = patientDetails.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF2C2C2C)
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Status
        Text(
            text = patientDetails.status,
            fontSize = 14.sp,
            color = patientDetails.statusColor,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Track Location Button
        OutlinedButton(
            onClick = { /* TODO: Implement track location */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFF666666)
            ),
            border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                modifier = Modifier.size(16.dp),
                tint = Color(0xFF666666)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Track location",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// Patient Information Section
@Composable
fun PatientInfoSection(patientDetails: Patient) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        DetailRow(
            label = "Age:",
            value = patientDetails.age
        )

        DetailRow(
            label = "Address:",
            value = patientDetails.address
        )
    }
}

// Caretaker Details Section
@Composable
fun CaretakerDetailsSection(caretaker: Patient) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Section Header
        Text(
            text = "Caretaker details",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF4CAF50)
        )

        DetailRow(
            label = "Name:",
            value = caretaker.name
        )

        DetailRow(
            label = "Address:",
            value = caretaker.address
        )

        DetailRow(
            label = "Contact:",
            value = caretaker.address
        )
    }
}

// Reusable Detail Row Component
@Composable
fun DetailRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
       // crossAxisAlignment = Alignment.Top
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF2C2C2C),
            modifier = Modifier.width(80.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = value,
            fontSize = 14.sp,
            color = Color(0xFF666666),
            modifier = Modifier.weight(1f),
            lineHeight = 20.sp
        )
    }
}

// Bottom Bar Component
@Composable
fun PatientDetailsBottomBar(
    onInformCaretakerClick: () -> Unit,
    onTrackClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Inform Caretaker Button
            Button(
                onClick = onInformCaretakerClick,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE3F2FD),
                    contentColor = Color(0xFF1976D2)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Inform",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Inform Caretaker",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Track Button
            Button(
                onClick = onTrackClick,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0A2540)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Track",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Track",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}