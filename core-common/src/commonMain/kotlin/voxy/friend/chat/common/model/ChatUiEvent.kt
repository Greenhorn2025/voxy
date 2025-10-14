package voxy.friend.chat.common.model

open class ChatUiEvent {
    data class SendMessage(val content: String) : ChatUiEvent()
    data class ReceiveMessage(val message: Message) : ChatUiEvent()
    data class RetryMessage(val messageId: String) : ChatUiEvent()

    object ShowEmojiPicker : ChatUiEvent()
    object ShowMoreMenu : ChatUiEvent()
    data class ToggleDisappearingChats(val enabled: Boolean) : ChatUiEvent()
    object HideMoreMenu : ChatUiEvent()
    object HideEmojiPicker : ChatUiEvent()
    object DisappearingChatsEnabled : ChatUiEvent()
    object LoadMessages : ChatUiEvent()
}