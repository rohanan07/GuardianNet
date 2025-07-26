package com.example.guardiannet.Registration

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guardiannet.R
import com.example.guardiannet.Registration.components.OtpInputField
import com.example.guardiannet.Registration.components.ResendOtpTimer
import com.example.guardiannet.ui.theme.Poppins
import kotlinx.coroutines.launch

@Preview
@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("ContextCastToActivity")
@Composable
fun AuthScreen(
    // viewModel: AuthViewModel = koinViewModel(),
    //navController: NavController
) {
    // val uiState by viewModel.uiState.collectAsState()
    var phoneNumber by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var isOtpError by remember { mutableStateOf(false) }
    var isCodeSent by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val activity = context as? Activity

    Column(
        modifier = Modifier.fillMaxSize().background(color = Color(0xFFECEFF1)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(120.dp))
        Image(
            painter = painterResource(R.drawable.guardiannetlogo),
            contentDescription = "App Logo",
            modifier = Modifier.height(100.dp).width(100.dp)
        )
        Spacer(modifier = Modifier.height(60.dp))

        AnimatedContent(
            targetState = isCodeSent,
            transitionSpec = {
                slideInHorizontally { fullWidth -> fullWidth } + fadeIn() with
                        slideOutHorizontally { fullWidth -> -fullWidth } + fadeOut()
            },
            label = "AuthSwitcher"
        ) { codeSent ->
            if (!codeSent) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Enter your phone number to continue",
                        fontFamily = Poppins,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFE9EEF2), RoundedCornerShape(12.dp))
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "+91",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color(0xFF607D8B)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Divider(
                            color = Color(0xFF607D8B),
                            modifier = Modifier
                                .height(24.dp)
                                .width(1.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        BasicTextField(
                            value = phoneNumber,
                            onValueChange = { phoneNumber = it },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            textStyle = LocalTextStyle.current.copy(
                                color = Color(0xFF607D8B),
                                fontSize = 16.sp,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            // TODO: Implement phone verification when ViewModel is available
                            // activity?.let { nonNullActivity ->
                            //     viewModel.sendVerificationCode("+91$phoneNumber", nonNullActivity)
                            // } ?: Log.e("PhoneAuth", "Activity is null")

                            // For now, just simulate sending OTP
                            isLoading = true
                            // Simulate network delay
                            kotlinx.coroutines.GlobalScope.launch {
                                kotlinx.coroutines.delay(1000)
                                isLoading = false
                                isCodeSent = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A2540)),
                        enabled = !isLoading
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = Color.White
                            )
                        } else {
                            Text(
                                "Send OTP",
                                fontFamily = Poppins,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { isCodeSent = false }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Edit Phone Number", fontFamily = Poppins)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OtpInputField(
                        otpLength = 6,
                        error = isOtpError,
                        onOtpComplete = { enteredOtp ->
                            otp = enteredOtp
                            isOtpError = false
                            // TODO: Implement OTP verification when ViewModel is available
                            // viewModel.verifyCode(enteredOtp)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ResendOtpTimer {
                        // TODO: Implement resend OTP when ViewModel is available
                        // activity?.let { viewModel.sendVerificationCode("+91$phoneNumber", it) }
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            // TODO: Implement OTP verification when ViewModel is available
                            // viewModel.verifyCode(otp)

                            // For now, just simulate verification
                            if (otp.length == 6) {
                                // Simulate successful verification
                                // navController.navigate("home") // or wherever you want to navigate
                            } else {
                                isOtpError = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A2540))
                    ) {
                        Text(
                            "Verify OTP",
                            fontFamily = Poppins,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        // TODO: Handle UI states when ViewModel is available
        /*
        when (uiState) {
            is AuthState.Loading -> SendingOtp()
            is AuthState.Success -> {
                LaunchedEffect(Unit) { viewModel.onAuthSuccess() }
            }

            is AuthState.UserExists -> {
                viewModel.checkUserAndNavigate(navController)
            }

            is AuthState.Error -> {
                isOtpError = true
                LaunchedEffect(uiState) { context.showErrorDialog("Something went wrong") }
            }

            else -> {}
        }
        */

        // Show loading indicator if needed
        if (isLoading) {
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator()
        }

        // Show error message if OTP is incorrect
        if (isOtpError) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Invalid OTP. Please try again.",
                color = Color.Red,
                fontFamily = Poppins,
                fontSize = 12.sp
            )
        }
    }
}
