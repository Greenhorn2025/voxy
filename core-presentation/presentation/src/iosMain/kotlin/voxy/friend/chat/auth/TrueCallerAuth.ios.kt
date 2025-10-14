package voxy.friend.chat.auth

actual class TrueCallerAuth {
    actual fun initialize(config: TruecallerConfig) {
    }

    actual fun startAuthentication() {
    }

    actual fun isUsable(): Boolean {
        TODO("Not yet implemented")
    }

    actual fun setCallback(callback: TrueCallerCallback) {
    }
}