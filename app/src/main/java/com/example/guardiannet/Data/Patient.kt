package com.example.guardiannet.Data

data class Patient(
    val id: String,
    val name: String,
    val status: String = "In Safe Zone",
    val lastCheckIn: String,
    val profileImage: String? = null
)