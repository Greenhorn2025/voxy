package voxy.friend.chat.socket

import voxy.friend.chat.common.model.chat.MessageEntity

interface SocketManagerListener {
    suspend fun connect(url: String, autoReconnect: Boolean = true)
    suspend fun connectInternal(url: String)
    suspend fun scheduleReconnect(url: String)
    suspend fun listenToMessages()
    suspend fun handleDisconnection()
    suspend fun sendMessage(message: MessageEntity)
    suspend fun disconnect()
    fun isConnected() : Boolean
    fun cleanup()
}

suspend inline fun <reified T> SocketManagerListener.sendObject(data: T) {

}