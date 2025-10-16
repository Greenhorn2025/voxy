package voxy.friend.chat.socket

sealed class SocketState {
    object Connecting : SocketState()
    object Connected : SocketState()
    object Disconnected : SocketState()
    data class Error(val message: String) : SocketState()
}