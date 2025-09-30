package voxy.friend.chat.chat

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BotChatScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier,
        topBar = {
            ChatTopBar()
        }, bottomBar = {
            BottomMessageInput()
        }) { paddingValues ->
        ChatContent(modifier = Modifier.padding(paddingValues))
    }
}