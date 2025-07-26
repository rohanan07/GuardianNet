package com.example.guardiannet.Data

import androidx.compose.ui.graphics.Color

data class Patient(
    val id: String = "",
    val name: String = "",
    val status: String = "",
    val statusColor: Color = Color(0xFFFF4C4C),
    val age: String = "",
    val address: String = "",
    val profileImage: String? = null,
    val caretaker: Caretaker,
    val latitude: Double = 28.6139,
    val longitude: Double = 77.2090,
)