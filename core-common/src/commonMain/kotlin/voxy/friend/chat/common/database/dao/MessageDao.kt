package voxy.friend.chat.common.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import voxy.friend.chat.common.model.chat.MessageEntity
import voxy.friend.chat.common.model.chat.PendingMessageEntity

@Dao
interface MessageDao {
    @Query("SELECT * FROM messages ORDER BY cid DESC")
    fun getAllMessages(): Flow<List<MessageEntity>>

    @Query("SELECT * FROM messages WHERE sid = :userId OR rid = :userId ORDER BY cid DESC")
    fun getMessagesByUser(userId: Long): Flow<List<MessageEntity>>

    @Query("SELECT * FROM messages WHERE id = :messageId")
    suspend fun getMessageById(messageId: String): MessageEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(messages: List<MessageEntity>)

    @Update
    suspend fun updateMessage(message: MessageEntity)

    @Delete
    suspend fun deleteMessage(message: MessageEntity)

    @Query("DELETE FROM messages WHERE id = :messageId")
    suspend fun deleteMessageById(messageId: String)

//    @Query("UPDATE messages SET isSent = 1 WHERE id = :id")
//    suspend fun markAsSent(id: String)

//    @Query("UPDATE messages SET isDelivered = 1 WHERE id = :id")
//    suspend fun markAsDelivered(id: String)

//    @Query("UPDATE messages SET isRead = 1 WHERE id = :id")
//    suspend fun markAsRead(id: String)

    @Query("DELETE FROM messages")
    suspend fun deleteAllMessages()

    // Pending Messages
    @Query("SELECT * FROM pending_messages ORDER BY timestamp ASC")
    suspend fun getPendingMessages(): List<PendingMessageEntity>

    @Query("SELECT * FROM pending_messages WHERE retryCount < 5 ORDER BY timestamp ASC")
    suspend fun getPendingMessagesWithRetryLimit(): List<PendingMessageEntity>

    @Insert
    suspend fun insertPendingMessage(message: PendingMessageEntity): Long

    @Delete
    suspend fun deletePendingMessage(message: PendingMessageEntity)

    @Query("DELETE FROM pending_messages WHERE localId = :id")
    suspend fun deletePendingMessageById(id: Long)

    @Query("UPDATE pending_messages SET retryCount = retryCount + 1 WHERE localId = :id")
    suspend fun incrementRetryCount(id: Long)

    @Query("DELETE FROM pending_messages")
    suspend fun deleteAllPendingMessages()

    // Search
//    @Query("SELECT * FROM messages WHERE text LIKE '%' || :query || '%' ORDER BY timestamp DESC")
//    fun searchMessages(query: String): Flow<List<MessageEntity>>
}