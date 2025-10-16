package voxy.friend.chat.common.model.chat

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pending_messages")
data class PendingMessageEntity(
    @PrimaryKey(autoGenerate = true)
    val localId: Long = 0,
    val text: String,
    val senderId: Long,
    val receiverId: Long? = null,
    val timestamp: Long,
    val retryCount: Int = 0,
    val type: String = "text",
    val metadata: String? = null
)