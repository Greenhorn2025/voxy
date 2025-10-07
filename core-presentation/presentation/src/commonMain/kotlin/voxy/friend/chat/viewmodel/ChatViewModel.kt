package voxy.friend.chat.viewmodel

import voxy.friend.chat.common.model.ChatUiEvent
import voxy.friend.chat.common.model.ChatUiState
import voxy.friend.chat.network.NetworkMonitor

class ChatViewModel(
    networkMonitor: NetworkMonitor
) : BaseViewModel<ChatUiState, ChatUiEvent>(networkMonitor) {

    fun handleEvent(event: ChatUiEvent) {
        when (event) {
            is ChatUiEvent.SendMessage -> {
                // Handle send message event
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
            is ChatUiEvent.HideMoreMenu -> {
                hideMoreOptions()
            }
            else ->{
                hideEmojiPicker()
            }
        }
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