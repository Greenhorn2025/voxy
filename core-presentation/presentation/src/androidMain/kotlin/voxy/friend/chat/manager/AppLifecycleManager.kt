package voxy.friend.chat.manager

import android.content.Context
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import voxy.friend.chat.repository.ChatRepository
import voxy.friend.chat.workmanager.MessageSyncWorker

class AppLifecycleManager(
    private val context: Context,
    private val chatRepository: ChatRepository
) : DefaultLifecycleObserver {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private var isAppInForeground = false

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        if (!isAppInForeground) {
            isAppInForeground = true
            onAppForegrounded()
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        if (isAppInForeground) {
            isAppInForeground = false
            onAppBackgrounded()
        }
    }

    private fun onAppForegrounded() {
        scope.launch {
            // App is open - Connect WebSocket
//            val userId = getUserId()
//            val token = getToken()

//            if (userId.isNotEmpty() && token.isNotEmpty()) {
                chatRepository.connectWebSocket(
                    url = "wss://wss-dev.voxy.in"
                )

                // Start observing messages
//                launch {
//                    chatRepository.observeIncomingMessages()
//                }
//            }

            // Cancel background sync since we're now connected
//            MessageSyncWorker.cancel(context)
        }
    }

    private fun onAppBackgrounded() {
        scope.launch {
            // App is in background/killed - Disconnect WebSocket
            chatRepository.disconnectWebSocket()

            // Schedule background sync via WorkManager
            MessageSyncWorker.schedule(context)
        }
    }
}