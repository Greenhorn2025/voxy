package voxy.friend.chat.common.model

import voxy.friend.chat.common.model.chat.MessageEntity

data class ChatUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val messages: List<MessageEntity> = emptyList(),
    val isAtBottom: Boolean = true,
    val isConnected: Boolean = true,
    val showEmojiBottomSheet: Boolean = false,
    val showMoreMenu: Boolean = false,
    val disappearingChatsEnabled: Boolean = false,
)
