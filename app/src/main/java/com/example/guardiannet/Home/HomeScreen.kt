package com.example.guardiannet.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guardiannet.Data.Patient
import com.example.guardiannet.R

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onPatientClick: (Patient) -> Unit = {},
    onAddPatientClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    // Hardcoded data - replace with actual data when backend is ready
    val myPatients = listOf(
        Patient(
            id = "1",
            name = "Vinod Krishna",
            lastCheckIn = "2 mins ago",
        ),
        Patient(
            id = "2",
            name = "Yash Kamble",
            lastCheckIn = "5 hours ago",
        )
    )

    val nearbyRequests = listOf(
        Patient(
            id = "3",
            name = "Lorem Ipsum",
            lastCheckIn = "Just now",
        )
    )

    Scaffold(
        topBar = {
            HomeTopBar(onProfileClick = onProfileClick)
        },
        bottomBar = {
            HomeBottomBar(onAddPatientClick = onAddPatientClick)
        },
        containerColor = Color(0xFFF5F5F7)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }

            // My Patients Section
            if (myPatients.isNotEmpty()) {
                item {
                    SectionHeader(title = "My patients")
                }
                items(myPatients) { patient ->
                    PatientCard(
                        patient = patient,
                        onClick = { onPatientClick(patient) }
                    )
                }
            }

            // Nearby Help Requests Section
            if (nearbyRequests.isNotEmpty()) {
                item {
                    SectionHeader(title = "Nearby help requests")
                }
                items(nearbyRequests) { patient ->
                    PatientCard(
                        patient = patient,
                        onClick = { onPatientClick(patient) },
                        isNearbyRequest = true
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

// Top Bar Component
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(onProfileClick: () -> Unit) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(R.drawable.guardiannetlogo) , contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = onProfileClick) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = Color(0xFF2C2C2C)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

// Bottom Bar Component
@Composable
fun HomeBottomBar(onAddPatientClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarItem(
                icon = Icons.Default.Home,
                label = "Home",
                isSelected = true,
                onClick = { /* Already on home */ }
            )

            // Add Patient Button
            Button(
                onClick = onAddPatientClick,
                modifier = Modifier
                    .height(48.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0A2540)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Patient",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Add Patient",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            BottomBarItem(
                icon = Icons.Default.Person,
                label = "Profile",
                isSelected = false,
                onClick = { /* Navigate to profile */ }
            )
        }
    }
}

// Bottom Bar Item Component
@Composable
fun BottomBarItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) Color(0xFF0A2540) else Color(0xFF9E9E9E),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = if (isSelected) Color(0xFF0A2540) else Color(0xFF9E9E9E),
            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
        )
    }
}

// Section Header Component
@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF2C2C2C),
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

// Patient Card Component
@Composable
fun PatientCard(
    patient: Patient,
    onClick: () -> Unit,
    isNearbyRequest: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image Placeholder
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE1F5FE)),
                contentAlignment = Alignment.Center
            ) {
                // Replace with actual image when available
                // AsyncImage(
                //     model = patient.profileImage,
                //     contentDescription = "${patient.name} profile",
                //     modifier = Modifier.fillMaxSize(),
                //     contentScale = ContentScale.Crop
                // )
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = Color(0xFF0288D1),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Patient Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = patient.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF2C2C2C),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))

                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = "Time",
                        tint = Color(0xFF9E9E9E),
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = patient.lastCheckIn,
                        fontSize = 12.sp,
                        color = Color(0xFF9E9E9E)
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))


            Spacer(modifier = Modifier.width(8.dp))

            // Arrow Icon
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "View Details",
                tint = Color(0xFF9E9E9E),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

// Preview
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen()
    }
}

// Usage Example
@Composable
fun HomeScreenWithNavigation() {
    HomeScreen(
        onPatientClick = { patient ->
            // Navigate to patient details
            println("Clicked on patient: ${patient.name}")
        },
        onAddPatientClick = {
            // Navigate to add patient screen
            println("Add patient clicked")
        },
        onProfileClick = {
            // Navigate to profile screen
            println("Profile clicked")
        }
    )
}