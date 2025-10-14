package voxy.friend.chat.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import multiplatform.network.cmptoast.showToast
import network.chaintech.sdpcomposemultiplatform.sdp
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import voxy.core_presentation.presentation.generated.resources.Res
import voxy.core_presentation.presentation.generated.resources.logo
import voxy.friend.chat.common.color.AppColors
import voxy.friend.chat.extension.getMapValue
import voxy.friend.chat.extension.getStringValue
import voxy.friend.chat.otpless.OtplessState
import voxy.friend.chat.viewmodel.OTPLessViewModel
import voxy.friend.chat.viewmodel.PhoneHintViewModel

@Composable
fun OTPVerifyScreen(
    modifier: Modifier = Modifier,
    phoneNumber: String,
    onEditClick: (String) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    val phoneHintViewModel = koinViewModel<PhoneHintViewModel>()
    val state by phoneHintViewModel.state.collectAsStateWithLifecycle()

    val otplessViewModel = koinViewModel<OTPLessViewModel>()
    val otplessState by otplessViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(otplessState.otplessState) {
        when (val state = otplessState.otplessState) {
            is OtplessState.InitiateSuccess -> {

            }

            is OtplessState.VerifySuccess -> {
                // Handle verified success
                otplessViewModel.verifyOTPFromBE(
                    token = state.data.getMapValue("data")?.getStringValue("token") ?: ""
                )
            }

            is OtplessState.Error -> {
                // Handle error based on type
                showToast(state.errorMessage)
            }

            else -> {}
        }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { otplessState.otpVerified }
            .distinctUntilChanged()
            .filter { it }
            .collect { otpVerified ->
                if (otpVerified) {
                    showToast("OTP verified successfully")
                    onDismiss.invoke()
                }
            }
    }


    Column(
        modifier = modifier.fillMaxWidth().imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.width(120.sdp).height(60.sdp),
            painter = painterResource(Res.drawable.logo),
            alignment = Alignment.Center,
            contentDescription = "image description"
        )

        Spacer(modifier = Modifier.height(2.sdp))

        Text(
            text = "Enter OTP",
            color = AppColors.OnPrimary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(start = 16.sdp, bottom = 5.sdp),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(2.sdp))

        SentToPhoneNumberSimple(phoneNumber = phoneNumber, editNumber = {
            onEditClick.invoke(it)
        })

        Spacer(modifier = Modifier.height(5.sdp))

        OTPNumberInput(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.sdp))

        Spacer(modifier = Modifier.height(14.sdp))

        OTPResendTimer(
            onResendClick = {
                otplessViewModel.sendOtp(phoneNumber, "91")
            }
        )

        Spacer(modifier = Modifier.height(2.sdp))

        Button(
            onClick = {
                otplessViewModel.verifyOTPByOTPLess(state.otpNumber, phoneNumber, "91")
            },
            modifier = Modifier
                .padding(vertical = 15.sdp)
                .fillMaxWidth()
                .height(40.sdp)
                .padding(horizontal = 10.sdp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (state.otpNumber.length == 4) AppColors.priceHighlight else Color(
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
                text = "Submit OTP",
                style = MaterialTheme.typography.bodyMedium,
                color = if (state.otpNumber.length == 4) AppColors.Bg else AppColors.disableColor
            )
        }
    }
}