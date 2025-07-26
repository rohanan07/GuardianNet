package com.example.guardiannet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.guardiannet.PatientTracking.PatientLocationScreen
import com.example.guardiannet.PatientTracking.TrackPatientScreen
import com.example.guardiannet.ui.theme.GuardianNetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuardianNetTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    //call the app navigation here
                    PatientLocationScreen()
                }
            }
        }
    }
}

