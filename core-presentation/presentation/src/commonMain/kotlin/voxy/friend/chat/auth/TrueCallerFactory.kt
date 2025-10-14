package voxy.friend.chat.auth

expect class TrueCallerFactory {
    fun create(): TrueCallerRepository
}