package voxy.friend.chat.networkinspector

import android.content.Context

actual fun createNetworkInspector(): NetworkInspector {
    val context: Context = KoinHelper.koin.get()
    return AndroidNetworkInspector(context)
}

