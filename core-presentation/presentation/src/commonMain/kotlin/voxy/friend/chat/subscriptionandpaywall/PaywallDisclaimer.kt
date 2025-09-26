package voxy.friend.chat.subscriptionandpaywall

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import voxy.friend.chat.common.color.AppColors


@Composable
fun PaywallDisclaimer() {
    Text(
        text = "3 day trial, auto-pays ₹199 every month, cancel anytime",
        color = AppColors.disableColor,
        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}