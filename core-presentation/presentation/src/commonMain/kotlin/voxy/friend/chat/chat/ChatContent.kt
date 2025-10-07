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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import voxy.friend.chat.common.enum.MessageStatus
import voxy.friend.chat.common.model.Message

@Composable
fun ChatContent(modifier: Modifier) {
    val maxBubbleWidth = 280.dp
    val messages = remember {
        listOf(
            Message(1, "So... you're the guy everyone keeps talking about?", "17:23", false, dateLabel = "Yesterday"),
            Message(2, "Haha yeah.. it's me alright.", "5:23 PM", true, MessageStatus.READ),
            Message(3, "Do you know Gandhiji?", "17:23", true, MessageStatus.DELIVERED),
            Message(4, "He was a cool dude", "17:23", true, MessageStatus.SENT),
            Message(5, "He loved salt.", "17:23", true, MessageStatus.SENDING),
            Message(6, "Hello?", "17:23", true, MessageStatus.FAILED),
            Message(7, "Waow much knowledge 😊", "17:23", false, dateLabel = "Today"),
            Message(8, "Gandhiji was pretty awesome", "5:23 PM", false)
        )
    }

    BoxWithConstraints(
        modifier = modifier
            .background(Color(0xFF000000))
    ){
        val listState = rememberLazyListState()

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
                    message.dateLabel?.let { label ->
                        DateSeparator(label)
                    }

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
