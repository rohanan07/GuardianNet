package com.example.guardiannet.Profile

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guardiannet.ui.theme.Poppins

import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter


@Preview
@Composable
fun PatientProfileScreen() {

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = { PTopAppBar() },
        bottomBar = { PBottomNavigation() },
        containerColor = Color(0xFFF1F3F6) // Light grey background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp),
            //horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileScreenContent()
        }
    }
}
@Composable
fun ProfileScreenContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // Avatar
        Image(
            painter = rememberAsyncImagePainter("https://randomuser.me/api/portraits/men/32.jpg"),
            contentDescription = null,
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Name
        Text(
            text = "Yash Karande",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0A2540)
        )

        Spacer(modifier = Modifier.height(6.dp))

        // Edit profile
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { /* Edit profile logic */ }
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                tint = Color(0xFF2F80ED),
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "edit profile",
                fontSize = 14.sp,
                color = Color(0xFF2F80ED),
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Caretaker Section
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "My Caretaker",
                color = Color(0xFF219653), // Green
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            CaretakerDetailRow(label = "Name:", value = "Rohan Arun Nalawade")
            Spacer(modifier = Modifier.height(12.dp))
            CaretakerDetailRow(
                label = "Address:",
                value = "House No. 17, Raghunandan Nagar,\nNear Sinhgad College, Vadgaon Budruk,\nPune - 411041, Maharashtra"
            )
            Spacer(modifier = Modifier.height(12.dp))
            CaretakerDetailRow(label = "Contact:", value = "8975785013")
        }
    }
}
@Composable
fun CaretakerDetailRow(label: String, value: String) {
    Row(
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color(0xFF0A2540),
            modifier = Modifier.width(80.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            fontSize = 14.sp,
            color = Color(0xFF4F4F4F)
        )
    }
}

@Composable
fun PTopAppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White),
    ) {
        IconButton(
            onClick = {  },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }

        // Title
        Text(
            text = "Profile",
            fontFamily = Poppins,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
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