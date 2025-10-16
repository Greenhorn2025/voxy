package voxy.friend.chat.viewmodel

import voxy.friend.chat.common.model.ChatUiEvent
import voxy.friend.chat.common.model.ChatUiState
import voxy.friend.chat.datastore.VoxyDataStoreImpl
import voxy.friend.chat.network.NetworkMonitor
import voxy.friend.chat.usecase.ChatMessageUseCase

class ChatViewModel(
    networkMonitor: NetworkMonitor,
    dataStore: VoxyDataStoreImpl,
    private val chatMessageUseCase: ChatMessageUseCase
) : BaseViewModel<ChatUiState, ChatUiEvent>(networkMonitor, dataStore) {

    suspend fun handleEvent(event: ChatUiEvent) {
        when (event) {
            is ChatUiEvent.SendMessage -> {
                // Handle send message event
                sendMessage(event.content, 1011)
            }
            is ChatUiEvent.ReceiveMessage -> {
                // Handle receive message event
            }
            is ChatUiEvent.RetryMessage -> {
                // Handle retry message event
            }
            is ChatUiEvent.LoadMessages -> {
                // Handle load messages event
            }
            is ChatUiEvent.ShowEmojiPicker -> {
                showEmojiPicker()
            }
            is ChatUiEvent.ShowMoreMenu -> {
                showMoreOptions()
            }
            is ChatUiEvent.DisappearingChatsEnabled ->{
                updateState { it.copy(disappearingChatsEnabled = !it.disappearingChatsEnabled) }
            }
            is ChatUiEvent.HideMoreMenu -> {
                hideMoreOptions()
            }
            else ->{
                hideEmojiPicker()
            }
        }
    }

    suspend fun sendMessage(text: String, userId: Long) {
        chatMessageUseCase.sendMessage(text, userId)
    }

    private fun showMoreOptions() {
        updateState { it.copy(showMoreMenu = true) }
    }

    private fun hideMoreOptions() {
        updateState { it.copy(showMoreMenu = false) }
    }

    private fun hideEmojiPicker() {
        updateState { it.copy(showEmojiBottomSheet = false) }
    }

    private fun showEmojiPicker() {
        updateState { it.copy(showEmojiBottomSheet = true) }
    }

    override fun setDefaultState() = ChatUiState()
}