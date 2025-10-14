package voxy.friend.chat.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import multiplatform.network.cmptoast.showToast
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import voxy.core_presentation.presentation.generated.resources.Res
import voxy.core_presentation.presentation.generated.resources.google_icon
import voxy.core_presentation.presentation.generated.resources.logo
import voxy.friend.chat.common.color.AppColors
import voxy.friend.chat.otpless.OtplessState
import voxy.friend.chat.viewmodel.OTPLessViewModel
import voxy.friend.chat.viewmodel.PhoneHintViewModel

@Composable
fun AuthenticationScreen(
    modifier: Modifier = Modifier,
    initialPhoneNumber: String = "",
    onGetOtpClick: (String) -> Unit = {},
    onGoogleSignInClick: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    val otplessViewModel = koinViewModel<OTPLessViewModel>()
    val otplessState by otplessViewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(otplessState.otplessState) {
        when (val state = otplessState.otplessState) {
            is OtplessState.InitiateSuccess -> {
                // Handle initiate success
                println("OTP sent! Order ID: ${state.orderId}")
            }
            is OtplessState.Error -> {
                // Handle error based on type
                showToast(state.errorMessage)
//                handleError(state)
            }
            else -> {}
        }
    }

    Column(
        modifier = modifier.imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val phoneHintViewModel = koinViewModel<PhoneHintViewModel>()
        val state by phoneHintViewModel.state.collectAsStateWithLifecycle()

        Image(
            modifier = Modifier.width(120.sdp).height(60.sdp),
            painter = painterResource(Res.drawable.logo),
            contentDescription = "image description"
        )

        Spacer(modifier = Modifier.height(2.sdp))

        Text(
            text = "Enter your mobile number",
            color = AppColors.OnPrimary,
            modifier = Modifier.align(Alignment.Start).padding(start = 16.sdp, bottom = 5.sdp),
            style = MaterialTheme.typography.bodyMedium
        )

        MobileNumberInput(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.sdp), initialPhoneNumber = initialPhoneNumber)

        Button(
            onClick = {
                if(state.phoneNumber.length != 10) return@Button
                keyboardController?.hide()
                scope.launch {
                    delay(400)
                    otplessViewModel.sendOtp(phoneNumber = state.phoneNumber, countryCode = "91")
                    onGetOtpClick.invoke(state.phoneNumber)
                }
            },
            modifier = Modifier
                .padding(vertical = 15.sdp)
                .fillMaxWidth()
                .height(40.sdp)
                .padding(horizontal = 10.sdp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (state.phoneNumber.length == 10) AppColors.priceHighlight else Color(
                    0xFF4A4A4A
                ), // Dark gray
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.sdp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.sdp,
                pressedElevation = 8.sdp
            )
        ) {
            Text(
                text = "Get OTP",
                style = MaterialTheme.typography.bodyMedium,
                color = if (state.phoneNumber.length == 10) AppColors.Bg else AppColors.disableColor
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(Color.Gray.copy(alpha = 0.5f))
            )

            Text(
                text = "OR",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 8.ssp,
                modifier = Modifier.padding(horizontal = 8.sdp)
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(Color.Gray.copy(alpha = 0.5f))
            )
        }

        Spacer(modifier = Modifier.height(10.sdp))

        Button(
            onClick = {
                onGoogleSignInClick.invoke()
            },
            modifier = Modifier
                .padding(horizontal = 10.sdp)
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColors.Secondary, // Dark gray
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(50.sdp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                GoogleLogo()

                Spacer(modifier = Modifier.width(8.sdp))

                Text(
                    text = "Sign in with Google",
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppColors.white
                )
            }
        }

        Spacer(modifier = Modifier.height(18.sdp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.sdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // First line - Terms and Privacy Policy
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "By continuing you agree to our ",
                    color = Color.Gray,
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center,
                    fontSize = 8.ssp
                )

                Text(
                    text = "Terms of Use",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall.copy(
                        textDecoration = TextDecoration.Underline
                    ),
                    fontSize = 8.ssp,
                    modifier = Modifier.clickable {
                        // Handle terms of use click
                    }
                )

                Text(
                    text = " and ",
                    color = Color.Gray,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 8.ssp,
                )

                Text(
                    text = "Privacy Policy",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall.copy(
                        textDecoration = TextDecoration.Underline
                    ),
                    fontSize = 8.ssp,
                    modifier = Modifier.clickable {
                        // Handle privacy policy click
                    }
                )
            }

            // Second line - Security message
            Text(
                text = "All your details are 100% safe & secure.",
                color = Color.Gray,
                style = MaterialTheme.typography.labelSmall,
                fontSize = 8.ssp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun GoogleLogo() {
    Image(
        modifier = Modifier.size(28.sdp),
        painter = painterResource(Res.drawable.google_icon),
        contentDescription = "image description",
        contentScale = ContentScale.None
    )
}
