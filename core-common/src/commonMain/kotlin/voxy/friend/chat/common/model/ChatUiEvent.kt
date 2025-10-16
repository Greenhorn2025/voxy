package voxy.friend.chat.common.model

import voxy.friend.chat.common.model.chat.MessageEntity

open class ChatUiEvent {
    data class SendMessage(val content: String) : ChatUiEvent()
    data class ReceiveMessage(val message: MessageEntity) : ChatUiEvent()
    data class RetryMessage(val messageId: String) : ChatUiEvent()

    object ShowEmojiPicker : ChatUiEvent()
    object ShowMoreMenu : ChatUiEvent()
    data class ToggleDisappearingChats(val enabled: Boolean) : ChatUiEvent()
    object HideMoreMenu : ChatUiEvent()
    object HideEmojiPicker : ChatUiEvent()
    object DisappearingChatsEnabled : ChatUiEvent()
    object LoadMessages : ChatUiEvent()
}