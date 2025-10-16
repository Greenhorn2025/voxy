package voxy.friend.chat.repository

import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import voxy.friend.chat.common.database.dao.MessageDao
import voxy.friend.chat.common.model.chat.MessageData
import voxy.friend.chat.common.model.chat.MessageEntity
import voxy.friend.chat.common.model.chat.PendingMessageEntity
import voxy.friend.chat.socket.SocketState
import voxy.friend.chat.socket.WebSocketManager
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

private const val TAG = "SocketStatus"

class ChatRepositoryImpl(
    private val webSocketManager: WebSocketManager,
    private val messageDao: MessageDao
) : ChatRepository {

    val messages: Flow<List<MessageEntity>> = messageDao.getAllMessages()
    val connectionState = webSocketManager.connectionState

    init {
        CoroutineScope(Dispatchers.IO).launch {
            connectionState.collectLatest { state ->
                when(state){
                    is SocketState.Connected -> {
                        Napier.d { "$TAG ✅ Connected to WebSocket" }
                    }
                    is SocketState.Disconnected -> {
                        Napier.d { "$TAG ❌ Disconnected from WebSocket" }
                        isConnected = false
                    }
                    is SocketState.Connecting -> {
                        Napier.d { "$TAG 🔄 Connecting to WebSocket..." }
                    }
                    is SocketState.Error -> {
                        Napier.d { "$TAG ⚠️ WebSocket error: ${state.message}" }
                        isConnected = false
                    }
                }
            }
        }
    }
    private var isConnected = false

    override fun getAllMessages(): Flow<List<MessageEntity>> {
        return messageDao.getAllMessages()
    }

    override fun getMessagesByUser(userId: Long): Flow<List<MessageEntity>> {
        return messageDao.getMessagesByUser(userId)
    }

    override suspend fun getMessageById(messageId: String): MessageEntity? {
        return messageDao.getMessageById(messageId)
    }

    override suspend fun insertMessage(message: MessageEntity) {
        messageDao.insertMessage(message)
    }

    override suspend fun insertMessages(messages: List<MessageEntity>) {
        messageDao.insertMessages(messages)
    }

    override suspend fun updateMessage(message: MessageEntity) {
        messageDao.updateMessage(message)
    }

    override suspend fun deleteMessage(message: MessageEntity) {
        messageDao.deleteMessage(message)
    }

    override suspend fun markAsSent(messageId: String) {
//        messageDao.markAsSent(messageId)
    }

    override suspend fun markAsDelivered(messageId: String) {
//        messageDao.markAsDelivered(messageId)
    }

    override suspend fun markAsRead(messageId: String) {
//        messageDao.markAsRead(messageId)
    }

    override suspend fun connectWebSocket(url: String) {
        if (isConnected) return

        try {
            webSocketManager.connect(url, true)
            isConnected = true

            // Send pending messages after connection
            sendPendingMessages()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun disconnectWebSocket() {
        if (!isConnected) return

        try {
            webSocketManager.disconnect()
            isConnected = false
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @OptIn(ExperimentalTime::class)
    override suspend fun sendMessage(text: String, userId: Long) {
        try {
            val message = MessageEntity(
                id = Clock.System.now().toEpochMilliseconds().toString(),
                data = MessageData(
                    msg = text,
                    sid = 123L,
                    rid = 1,
                    cid = Clock.System.now().toEpochMilliseconds()
                )
            )
//            Napier.d { "$TAG Sending message: ${message}" }

            // Try to send via WebSocket
            webSocketManager.sendMessage(message)

            // Save to database
//            messageDao.insertMessage(
//                MessageEntity(
//                    id = message.id,
//                    data = MessageData(
//                        msg = message.data.msg,
//                        sid = message.data.sid,
//                        rid = message.data.rid,
//                        cid = message.data.cid
//                    )
//                )
//            )

        } catch (e: Exception) {
            // If failed, save to pending messages
            Napier.d { e.message.toString() }
            messageDao.insertPendingMessage(
                PendingMessageEntity(
                    text = text,
                    senderId = userId,
                    timestamp = Clock.System.now().toEpochMilliseconds()
                )
            )
            throw e
        }
    }

    @OptIn(ExperimentalTime::class)
    private suspend fun sendPendingMessages() {
        try {
            val pendingMessages = messageDao.getPendingMessages()

            pendingMessages.forEach { pending ->
                try {
                    val message = MessageEntity(
                        id = Clock.System.now().toEpochMilliseconds().toString(),
                        data = MessageData(
                            msg = pending.text,
                            sid = 123L,
                            rid = 1,
                            cid = Clock.System.now().toEpochMilliseconds()
                        )
                    )

                    webSocketManager.sendMessage(message)

                    // Save to main messages
//                    messageDao.insertMessage(
//                        MessageEntity(
//                            id = message.id,
//                            data = MessageData(
//                                msg = message.data.msg,
//                                sid = message.data.sid,
//                                rid = message.data.rid,
//                                cid = message.data.cid
//                            )
//                        )
//                    )

                    // Remove from pending
                    messageDao.deletePendingMessage(pending)

                } catch (e: Exception) {
                    // Keep in pending and increment retry count
                    messageDao.incrementRetryCount(pending.localId)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun observeIncomingMessages() {
        messages.collectLatest { message ->
            // Save incoming message to database
            message.map { message ->
//                messageDao.insertMessage(
//                    MessageEntity(
//                        id = message.id,
//                        data = MessageData(
//                            msg = message.data.msg,
//                            sid = message.data.sid,
//                            rid = message.data.rid,
//                            cid = message.data.cid
//                        )
//                    )
//                )
            }
        }
    }

    override suspend fun clearAllMessages() {
        messageDao.deleteAllMessages()
        messageDao.deleteAllPendingMessages()
    }
}