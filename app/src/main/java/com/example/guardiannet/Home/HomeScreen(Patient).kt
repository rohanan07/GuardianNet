package com.example.guardiannet.Home

import android.Manifest
import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.guardiannet.Profile.CBottomNavigation
import com.example.guardiannet.Profile.CTopAppBar
import com.example.guardiannet.R
import com.example.guardiannet.ui.theme.Poppins
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState

@Preview
@Composable
fun PatientHomeScreen(){
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = { PTopAppBar() },
        bottomBar = {PBottomNavigation()},
        containerColor = Color(0xFFF1F3F6) // Light grey background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp),
            //horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PatientHomeContent()
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("MissingPermission")
@Composable
fun PatientHomeContent() {
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(
            LatLng(18.4575, 73.8500), // VIIT College (Kondhwa) as center
            15f,
            0f,
            0f
        )
    }

    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    LaunchedEffect(Unit) {
        locationPermissionState.launchPermissionRequest()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        // USER INFO
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberAsyncImagePainter("https://randomuser.me/api/portraits/men/32.jpg"),
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Yash Karande",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0A2540)
                )
                Row {
                    Text(
                        text = "my code: ",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "6x278y5",
                        fontSize = 14.sp,
                        color = Color(0xFF2F80ED), // blue
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // LOCATION TEXT
        Text(
            text = "Current location: VIIT College",
            fontSize = 14.sp,
            color = Color(0xFF0A2540)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // BUTTONS ROW
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /* TODO: Alert logic */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEB5757)),
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
            ) {
                Text("🔔 Help", fontSize = 16.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { /* TODO: Navigate home */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2F80ED)),
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
            ) {
                Text("📍 Take me Home", fontSize = 16.sp, color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // MAP
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = true)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PTopAppBar() {
    TopAppBar(
        // modifier = Modifier.height(120.dp),
        title = {
            Image(
                painter = painterResource(id = R.drawable.guardiannetlogo), // Replace with your logo
                contentDescription = "Inpage Logo",
                modifier = Modifier.width(120.dp).height(34.dp),
                //contentScale = ContentScale.Crop
            )
        },
        actions = {
            Icon(
                modifier = Modifier.padding(horizontal = 16.dp).size(24.dp),
                imageVector = Icons.Default.Notifications,
                contentDescription = "notifications",
                tint = Color(0xFF0A2540)
            )
        }
    )
}

// Bottom Bar Component
@Composable
fun PBottomNavigation() {
    Surface(
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(77.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Home
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    //navController.navigate(PayMatesScreens.HomeScreen.name)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Home,
                    contentDescription = "Home",
                    modifier = Modifier
                        .height(28.dp)
                        .width(28.dp),
                    tint = Color(0xFF607D8B)
                )
                Text(
                    text = "Home",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Poppins,
                    color = Color(0xFF607D8B)
                )
            }

            // Profile
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    //navController.navigate(PayMatesScreens.ProfileScreen.name)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .height(28.dp)
                        .width(28.dp),
                    tint = Color(0xFF607D8B)
                )
                Text(
                    text = "Profile",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Poppins,
                    color = Color(0xFF607D8B)
                )
            }
        }
    }
}