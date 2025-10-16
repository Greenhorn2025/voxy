package voxy.friend.chat.chat

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import voxy.friend.chat.common.enum.MessageStatus

@Composable
fun MessageStatusIcon(status: MessageStatus) {
    when (status) {
        MessageStatus.SENDING -> {
            CircularProgressIndicator(
                modifier = Modifier.size(12.dp),
                color = Color.White.copy(alpha = 0.5f),
                strokeWidth = 1.5.dp
            )
        }
        MessageStatus.SENT -> {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Sent",
                tint = Color.White.copy(alpha = 0.5f),
                modifier = Modifier.size(14.dp)
            )
        }
        MessageStatus.DELIVERED -> {
            Row(modifier = Modifier.offset(x = (-2).dp)) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Delivered",
                    tint = Color.White.copy(alpha = 0.5f),
                    modifier = Modifier.size(14.dp)
                )
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Delivered",
                    tint = Color.White.copy(alpha = 0.5f),
                    modifier = Modifier
                        .size(14.dp)
                        .offset(x = (-6).dp)
                )
            }
        }
        MessageStatus.READ -> {
            Row(modifier = Modifier.offset(x = (-2).dp)) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Read",
                    tint = Color(0xFF3B82F6),
                    modifier = Modifier.size(14.dp)
                )
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Read",
                    tint = Color(0xFF3B82F6),
                    modifier = Modifier
                        .size(14.dp)
                        .offset(x = (-6).dp)
                )
            }
        }
        MessageStatus.FAILED -> {
            Icon(
                imageVector = Icons.Default.Error,
                contentDescription = "Failed",
                tint = Color(0xFFEF4444),
                modifier = Modifier.size(14.dp)
            )
        }

        MessageStatus.PENDING -> {
            CircularProgressIndicator(
                modifier = Modifier.size(12.dp),
                color = Color.White.copy(alpha = 0.5f),
                strokeWidth = 1.5.dp
            )
        }
    }
}