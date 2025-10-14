package voxy.friend.chat.auth

import voxy.friend.chat.common.di.ActivityContextProvider

actual class TrueCallerFactory(private val contextprovide : ActivityContextProvider) {
    actual fun create(): TrueCallerRepository {
        return AndroidTrueCallerRepository(
            contextprovide
        )
    }
}