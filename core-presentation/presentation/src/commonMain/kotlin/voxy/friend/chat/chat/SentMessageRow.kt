package voxy.friend.chat.chat

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import voxy.friend.chat.common.model.chat.MessageEntity

@Composable
fun SentMessageRow(modifier: Modifier, maxWidth : Dp, message: MessageEntity, onRetryClick: () -> Unit){
    ChatBubble(
        modifier = modifier,
        message = message,
        maxWidth = maxWidth,
        onRetryClick = onRetryClick
    )
}