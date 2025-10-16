package voxy.friend.chat.usecase

import voxy.friend.chat.common.model.chat.MessageEntity
import voxy.friend.chat.repository.ChatRepository

class ChatMessageUseCase(
    private val chatRepository: ChatRepository
) {
    fun getAllMessages() = chatRepository.getAllMessages()
    fun getMessagesByUser(userId: Long) = chatRepository.getMessagesByUser(userId)
    suspend fun getMessageById(messageId: String) = chatRepository.getMessageById(messageId)
    suspend fun connectWebSocket(url: String) = chatRepository.connectWebSocket(url)
    suspend fun disconnectWebSocket() = chatRepository.disconnectWebSocket()
    suspend fun observeIncomingMessages() = chatRepository.observeIncomingMessages()
    suspend fun sendMessage(text: String, userId: Long) = chatRepository.sendMessage(text, userId)
    suspend fun insertMessage(message: MessageEntity) = chatRepository.insertMessage(message)
    suspend fun insertMessages(messages: List<MessageEntity>) =
        chatRepository.insertMessages(messages)

    suspend fun updateMessage(message: MessageEntity) = chatRepository.updateMessage(message)
    suspend fun deleteMessage(message: MessageEntity) = chatRepository.deleteMessage(message)
    suspend fun markAsSent(messageId: String) = chatRepository.markAsSent(messageId)
    suspend fun markAsDelivered(messageId: String) = chatRepository.markAsDelivered(messageId)
    suspend fun markAsRead(messageId: String) = chatRepository.markAsRead(messageId)
    suspend fun clearAllMessages() = chatRepository.clearAllMessages()
}