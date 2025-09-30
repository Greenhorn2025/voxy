package voxy.friend.chat.chat

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import network.chaintech.sdpcomposemultiplatform.sdp
import voxy.friend.chat.common.color.AppColors

@Composable
fun OnlineStatusIndicator(
    modifier: Modifier = Modifier,
    isOnline: Boolean = true,
    text: String = "Online",
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAlpha"
    )

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.sdp)
    ) {
        // Status dot with animation
        Box(
            modifier = Modifier
                .size(8.sdp)
                .background(
                    color = if (isOnline) AppColors.darkHighlight.copy(alpha = pulseAlpha) else Color.Gray,
                    shape = CircleShape
                )
                .then(
                    if (isOnline) {
                        Modifier.animateContentSize()
                    } else Modifier
                )
        )

        // Status text
        Text(
            text = text,
            style = textStyle,
            color = if (isOnline) AppColors.darkHighlight else Color.Gray
        )
    }
}