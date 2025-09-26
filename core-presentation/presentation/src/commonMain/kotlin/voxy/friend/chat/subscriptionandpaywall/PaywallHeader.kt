package voxy.friend.chat.subscriptionandpaywall

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import voxy.friend.chat.common.color.AppColors
import voxy.friend.chat.common.typegraphy.VoxyTypography

@Composable
fun PaywallHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Send Unlimited Chats for",
            color = AppColors.OnPrimary,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "₹1",
            color = AppColors.priceHighlight,
            style = VoxyTypography.default().displayLarge
        )

        Text(
            text = "75k+ people bought the trial offer till now!",
            color = AppColors.darkHighlight,
            style = MaterialTheme.typography.bodySmall
        )
    }
}