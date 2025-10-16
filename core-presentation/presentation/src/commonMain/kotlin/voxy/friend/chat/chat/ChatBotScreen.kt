package voxy.friend.chat.chat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.koinInject
import voxy.friend.chat.common.model.ChatUiEvent
import voxy.friend.chat.emoji.EmojiBottomSheet
import voxy.friend.chat.viewmodel.ChatViewModel

@Composable
fun ChatBotScreen(modifier: Modifier = Modifier, onStartTrialClick: () -> Unit = {}) {
    val chatViewModel = koinInject<ChatViewModel>()
    val uiState by chatViewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier,
        topBar = {
            ChatTopBar(modifier = Modifier.fillMaxWidth(), showMoreMenu = uiState.showMoreMenu, // Pass state
                onMoreMenuClick = {
                    chatViewModel.handleEvent(event =ChatUiEvent.ShowMoreMenu)
                },
                onDismissMenu = {
                    chatViewModel.handleEvent(event =ChatUiEvent.HideMoreMenu)
                },
                onProfileClick = { /* Handle profile click */ },
                onDisappearingChatsToggle = { /* Handle toggle */ },
                onSetMoodClick = { /* Handle set mood click */ },
                onClearChatClick = { /* Handle clear chat click */ },
                onStartTrialClick = {
                    onStartTrialClick.invoke()
                },
                disappearingChatsEnabled = uiState.disappearingChatsEnabled // Example state
            )
        }, bottomBar = {
            BottomMessageInput(onEmojiClick = {
                chatViewModel.handleEvent(event =ChatUiEvent.ShowEmojiPicker)
            }, onSendMessage = { msg ->
                chatViewModel.handleEvent(event = ChatUiEvent.SendMessage(content = msg))
            })
        }
    ) { paddingValues ->
        ChatContent(modifier = Modifier.fillMaxSize().padding(paddingValues), chatViewModel)
    }

    if (uiState.showEmojiBottomSheet) {
        EmojiBottomSheet(
            onEmojiSelected = { chatViewModel.handleEvent(ChatUiEvent.HideEmojiPicker) },
            onDismiss = { chatViewModel.handleEvent(ChatUiEvent.HideEmojiPicker) }
        )
    }
}