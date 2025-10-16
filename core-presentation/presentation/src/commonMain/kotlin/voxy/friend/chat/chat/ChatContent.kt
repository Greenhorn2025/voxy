package voxy.friend.chat.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import voxy.friend.chat.viewmodel.ChatViewModel

@Composable
fun ChatContent(modifier: Modifier, chatViewModel: ChatViewModel) {
    val maxBubbleWidth = 280.dp
    val messages = chatViewModel.state.value.messages

    BoxWithConstraints(
        modifier = modifier
            .background(Color(0xFF000000))
    ){
        val listState = rememberLazyListState()

        LaunchedEffect(messages.size) {
            if (messages.isNotEmpty()) {
                listState.animateScrollToItem(messages.size - 1)
            }
        }

        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                state = listState,
                contentPadding = PaddingValues(
                    horizontal = 12.dp,
                    vertical = 16.dp
                )
            ) {

                item{
                    StartingChatBubble(modifier = Modifier.fillMaxWidth())
                }

                items(messages) { message ->
//                    message.dateLabel?.let { label ->
//                        DateSeparator(label)
//                    }

                    ChatMessageBubble(
                        modifier = Modifier.fillMaxWidth(),
                        maxWidth = maxBubbleWidth,
                        message = message,
                        onRetryClick = {

                        }
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}
