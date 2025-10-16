package voxy.friend.chat.socket

sealed class SocketEvent<T> {
    data class Message<T>(val data: T) : SocketEvent<T>()
    data class Error<T>(val message: String) : SocketEvent<T>()
    class Connected<T> : SocketEvent<T>()
    class Disconnected<T> : SocketEvent<T>()
}