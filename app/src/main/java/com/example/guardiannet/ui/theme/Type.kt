package com.example.guardiannet.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.guardiannet.R

// Set of Material typography styles to start with

val Poppins = FontFamily(
    Font(R.font.poppins_regular),
    Font(R.font.poppins_medium),
    Font(R.font.poppins_semibold),
    Font(R.font.poppins_bold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(         //for large body texts
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),
    headlineMedium = TextStyle(     //for primary text inside a container
        fontFamily = Poppins,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    ),
    titleMedium = TextStyle(        //for secondary text inside a container
        fontFamily = Poppins,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold
    ),
    labelLarge = TextStyle(         //for navigating texts
        fontFamily = Poppins,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
    ),
    labelMedium = TextStyle(    //for section headers
        fontFamily = Poppins,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    )
)