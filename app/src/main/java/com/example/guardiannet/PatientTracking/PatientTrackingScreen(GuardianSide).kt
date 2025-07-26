package com.example.guardiannet.PatientTracking

import android.Manifest
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.guardiannet.Data.Caretaker
import com.example.guardiannet.Data.Patient
import com.example.guardiannet.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

// Main Patient Location Screen
@Preview
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun PatientLocationScreen(
    onBackClick: () -> Unit = {},
    onMoreClick: () -> Unit = {},
    onInformCaretakerClick: () -> Unit = {}
) {
    // Hardcoded patient location data
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    LaunchedEffect(Unit) {
        locationPermissionState.launchPermissionRequest()
    }

     val caretaker = Caretaker(
            name = "Rohan Nalawade",
            address = "Sainagar, Pune",
            contact = "8975785013"
        )
    val patientLocation = Patient(
        id = "1",
        name = "Yash Karande",
        status = "Out of Safe Zone",
        statusColor = Color(0xFFE53E3E),
        latitude = 18.4646, // Pune coordinates
        longitude = 73.8797,
        caretaker = caretaker
    )

    Scaffold(
        topBar = {
            PatientLocationTopBar(
                onBackClick = onBackClick,
                onMoreClick = onMoreClick
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Map Section (placeholder)
            MapSection(
                patientLocation = patientLocation,
                modifier = Modifier.fillMaxSize()
            )

            // Patient Info Card (floating on top of map)
            PatientLocationCard(
                patientLocation = patientLocation,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(16.dp)
                    .zIndex(1f)
            )

            // Inform Caretaker Button (floating at bottom)
            Button(
                onClick = onInformCaretakerClick,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp)
                    .zIndex(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE3F2FD),
                    contentColor = Color(0xFF1976D2)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Inform",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Inform Caretaker",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

// Top Bar Component
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientLocationTopBar(
    onBackClick: () -> Unit,
    onMoreClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Track Patient",
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

// Patient Location Card (floating on map)
@Composable
fun PatientLocationCard(
    patientLocation: Patient,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE1F5FE)),
                contentAlignment = Alignment.Center
            ) {
                // Replace with actual image when available
                // AsyncImage(
                //     model = patientLocation.profileImage,
                //     contentDescription = "${patientLocation.name} profile",
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
                    text = patientLocation.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF2C2C2C)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = patientLocation.status,
                    fontSize = 14.sp,
                    color = patientLocation.statusColor,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

// Map Section Component (placeholder for actual map)
@Composable
fun MapSection(
    patientLocation: Patient,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val blueDotIcon = bitmapDescriptorFromVector(context, R.drawable.ic_blue_dot)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(
            LatLng(28.6139, 77.2090),
            15f,
            0f,
            0f
        )
    }
    Box(
        modifier = modifier.background(Color(0xFFF5F5F5))
    ) {
        // Map placeholder - replace with actual Google Maps or other map implementation
        // For now, showing a placeholder with street names similar to the design
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = true)
            ) {
                Marker(
                    state = MarkerState(position = LatLng(28.6139, 77.2090)),
                    icon = blueDotIcon,
                    title = "Patient's Location"
                )
            }
        }

        // TODO: Replace with actual map implementation
        // GoogleMap(
        //     modifier = modifier,
        //     cameraPositionState = rememberCameraPositionState {
        //         position = CameraPosition.fromLatLngZoom(
        //             LatLng(patientLocation.latitude, patientLocation.longitude),
        //             15f
        //         )
        //     }
        // ) {
        //     Marker(
        //         state = MarkerState(
        //             position = LatLng(patientLocation.latitude, patientLocation.longitude)
        //         ),
        //         title = patientLocation.name
        //     )
        // }
    }
}
