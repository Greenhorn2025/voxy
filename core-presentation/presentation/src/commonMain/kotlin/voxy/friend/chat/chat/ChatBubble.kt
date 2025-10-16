package voxy.friend.chat.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import voxy.friend.chat.common.enum.MessageStatus
import voxy.friend.chat.common.model.chat.MessageEntity

@Composable
fun ChatBubble(modifier: Modifier, maxWidth: Dp, message: MessageEntity, onRetryClick: () -> Unit) {
    Column(
        modifier = modifier,
        horizontalAlignment = if (message.data.isSender) Alignment.End else Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .widthIn(max = maxWidth)
                .padding(vertical = 2.dp),
            horizontalArrangement = if (message.data.isSender) Arrangement.End else Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = if (message.data.isSender) {
                            Brush.horizontalGradient(
                                colors = listOf(Color(0xFF4A5568), Color(0xFF4A5568))
                            )
                        } else {
                            Brush.horizontalGradient(
                                colors = listOf(Color(0xFF4A4458), Color(0xFF4A4458))
                            )
                        },
                        shape = RoundedCornerShape(
                            topStart = 18.dp,
                            topEnd = 18.dp,
                            bottomStart = if (message.data.isSender) 18.dp else 4.dp,
                            bottomEnd = if (message.data.isSender) 4.dp else 18.dp
                        )
                    )
                    .padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
                Column {
                    Text(
                        text = message.data.msg,
                        color = Color.White,
                        fontSize = 16.sp,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = message.data.cid.toString(),
                            color = Color.White.copy(alpha = 0.5f),
                            fontSize = 12.sp
                        )

                        if (message.data.isSender) {
                            Spacer(modifier = Modifier.width(4.dp))
                            MessageStatusIcon(message.data.sts)
                        }
                    }
                }
            }
        }

        // Failed message indicator
        if (message.data.sts == MessageStatus.FAILED && message.data.isSender) {
            Row(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable { onRetryClick() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Failed to send. ",
                    color = Color(0xFFEF4444),
                    fontSize = 13.sp
                )
                Text(
                    text = "Tap to retry",
                    color = Color(0xFF3B82F6),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}