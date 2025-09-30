package voxy.friend.chat.ai.network

import kotlinx.coroutines.flow.StateFlow

interface NetworkMonitor {
    val networkState: StateFlow<NetworkInfo>
    fun startMonitoring()
    fun stopMonitoring()
}