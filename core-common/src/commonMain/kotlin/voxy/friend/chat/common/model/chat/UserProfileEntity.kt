package voxy.friend.chat.common.model.chat

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey
    val userId: String,
    val name: String,
    val email: String,
    val avatar: String? = null,
    val phoneNumber: String? = null,
    val bio: String? = null,
    val lastSeen: Long,
    val isOnline: Boolean = false
)
