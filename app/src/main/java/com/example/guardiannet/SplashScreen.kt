package com.example.guardiannet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Preview
@Composable
fun SplashScreen() {

    LaunchedEffect(Unit) {
        delay(2000)
//        navController.navigate(InPageScreens.HomeScreen.name) {
//            popUpTo(InPageScreens.SplashScreen.name) { inclusive = true }
//        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.guardiannetlogo),
            contentDescription = "App Logo",
            modifier = Modifier.height(250.dp).width(250.dp)
        )
    }
}
