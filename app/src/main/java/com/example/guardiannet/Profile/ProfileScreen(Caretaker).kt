package com.example.guardiannet.Profile

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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.example.guardiannet.PatientTracking.TopAppBar
import com.example.guardiannet.ui.theme.Poppins

@Composable
fun CaretakerProfileScreen() {
    var name by remember { mutableStateOf("Rohan Nalawade") }
    var address by remember { mutableStateOf("House No. 17, Raghunandan Nagar,\nNear Sinhagad College, Vadgaon Budruk,\nPune - 411041, Maharashtra") }
    var contact by remember { mutableStateOf("8975785013") }
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = { CTopAppBar() },
        bottomBar = {CBottomNavigation()},
        containerColor = Color(0xFFF1F3F6) // Light grey background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp),
            //horizontalAlignment = Alignment.CenterHorizontally
        ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFF5F5F5))
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Profile Picture Section
                    Box(
                        modifier = Modifier.padding(vertical = 32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        // Profile Image (placeholder)
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE0E0E0))
                                .clickable { /* Handle photo change */ },
                            contentAlignment = Alignment.Center
                        ) {
                            // You can replace this with an actual image
                            Text(
                                text = "👤",
                                fontSize = 48.sp,
                                color = Color.Gray
                            )
                        }
                    }

                    // Change Photo Button
                    Text(
                        text = "change photo",
                        color = Color(0xFF2196F3),
                        fontSize = 14.sp,
                        modifier = Modifier
                            .clickable { /* Handle photo change */ }
                            .padding(bottom = 32.dp)
                    )

                    // Name Section
                    ProfileEditSection(
                        label = "Name",
                        value = name,
                        onValueChange = { name = it }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Address Section
                    ProfileEditSection(
                        label = "Address",
                        value = address,
                        onValueChange = { address = it },
                        minLines = 3,
                        maxLines = 5
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Contact Section
                    ProfileEditSection(
                        label = "Contact",
                        value = contact,
                        onValueChange = { contact = it },
                        keyboardType = KeyboardType.Phone
                    )

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }

    }
@Composable
fun ProfileEditSection(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    minLines: Int = 1,
    maxLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.weight(1f),
                    minLines = minLines,
                    maxLines = maxLines,
                    keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    ),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )

                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { /* Optional: Focus on text field */ }
                )
            }
        }
    }
}

@Composable
fun CTopAppBar() {
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
            text = "Track Patient",
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
fun CBottomNavigation() {
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

            // New Scan (centered and elevated)
            Box(
                modifier = Modifier
                    //.offset(y = (-16).dp)
                    .background(Color(0xFF0A2540), shape = RoundedCornerShape(20.dp))
                    .clickable {  }
                    .padding(horizontal = 24.dp, vertical = 10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Add Patient", color = Color.White, fontSize = 12.sp , fontFamily = Poppins)
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
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