package voxy.friend.chat.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import network.chaintech.sdpcomposemultiplatform.sdp
import voxy.friend.chat.common.color.AppColors

@Composable
fun SentToPhoneNumberSimple(
    phoneNumber: String,
    modifier: Modifier = Modifier,
    editNumber : (String) -> Unit = { }
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "sent to ",
                color = AppColors.disableColor,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 14.sp,
            )

            Text(
                text = "+91 $phoneNumber",
                color = Color.White,
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.width(4.sdp))

        Text(
            text = "Edit",
            color = AppColors.disableColor,
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodySmall,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable { editNumber(phoneNumber) }
        )
    }
}