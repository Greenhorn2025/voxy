package voxy.friend.chat.chat

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import voxy.friend.chat.common.model.Message

@Composable
fun ChatMessageBubble(
    modifier: Modifier = Modifier,
    maxWidth: Dp,
    message: Message,
    onRetryClick: () -> Unit
) {
    if(message.isSender){
        ReceivedMessageRow(
            modifier = modifier,
            message = message,
            maxWidth = maxWidth,
            onRetryClick = onRetryClick
        )
    }else {
        SentMessageRow(
            modifier = modifier,
            message = message,
            maxWidth = maxWidth,
            onRetryClick = onRetryClick
        )
    }
}