package voxy.friend.chat.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import voxy.friend.chat.common.color.AppColors

@Composable
fun OTPResendTimer(
    onResendClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var timeLeft by remember { mutableStateOf(30) }
    var isTimerActive by remember { mutableStateOf(true) }

    // Countdown timer effect
    LaunchedEffect(timeLeft, isTimerActive) {
        if (timeLeft > 0 && isTimerActive) {
            delay(1000L)
            timeLeft--
        } else if (timeLeft == 0) {
            isTimerActive = false
        }
    }

    // Reset timer when resend is clicked
    fun resetTimer() {
        timeLeft = 30
        isTimerActive = true
        onResendClick()
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isTimerActive && timeLeft > 0) {
            // Timer countdown state
            Text(
                text = "Didn't receive an OTP? ",
                color = AppColors.disableColor,
                fontSize = 14.sp,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "Resend in $timeLeft seconds",
                color = AppColors.white,
                fontSize = 14.sp,
                style = MaterialTheme.typography.labelSmall
            )
        } else {
            // Resend available state
            Text(
                text = "Didn't receive an OTP? ",
                color = AppColors.disableColor,
                fontSize = 14.sp,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "Resend OTP",
                color = AppColors.priceHighlight, // Purple accent color
                fontSize = 14.sp,
                style = MaterialTheme.typography.labelSmall.copy(
                    textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier.clickable { resetTimer() }
            )
        }
    }
}