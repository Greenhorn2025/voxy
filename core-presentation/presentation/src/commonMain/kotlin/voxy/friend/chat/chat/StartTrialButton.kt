package voxy.friend.chat.chat

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import voxy.friend.chat.common.color.AppColors

@Composable
fun StartTrialButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = "Start Trial",
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(32.sdp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.baseColor, // Purple color matching your image
            contentColor = Color.Black,
            disabledContainerColor = AppColors.baseColor.copy(alpha = 0.6f),
            disabledContentColor = Color.Black.copy(alpha = 0.6f)
        ),
        shape = RoundedCornerShape(8.sdp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 2.dp,
            pressedElevation = 8.dp,
            disabledElevation = 0.dp
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.ssp
            ),
            color = Color.Black
        )
    }
}