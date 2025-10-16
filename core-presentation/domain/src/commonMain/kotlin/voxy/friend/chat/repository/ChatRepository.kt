package voxy.friend.chat.repository

import kotlinx.coroutines.flow.Flow
import voxy.friend.chat.common.model.chat.MessageEntity

interface ChatRepository{

    suspend fun connectWebSocket(url: String)
    suspend fun disconnectWebSocket()
    suspend fun observeIncomingMessages()
    suspend fun sendMessage(text: String, userId: Long)
    fun getAllMessages(): Flow<List<MessageEntity>>
    fun getMessagesByUser(userId: Long): Flow<List<MessageEntity>>
    suspend fun getMessageById(messageId: String): MessageEntity?
    suspend fun insertMessage(message: MessageEntity)
    suspend fun insertMessages(messages: List<MessageEntity>)
    suspend fun updateMessage(message: MessageEntity)
    suspend fun deleteMessage(message: MessageEntity)
    suspend fun markAsSent(messageId: String)
    suspend fun markAsDelivered(messageId: String)
    suspend fun markAsRead(messageId: String)
    suspend fun clearAllMessages()
}