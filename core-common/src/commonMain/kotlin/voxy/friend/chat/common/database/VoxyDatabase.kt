package voxy.friend.chat.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import voxy.friend.chat.common.database.dao.ConversationDao
import voxy.friend.chat.common.database.dao.MessageDao
import voxy.friend.chat.common.database.dao.UserProfileDao
import voxy.friend.chat.common.model.chat.ConversationEntity
import voxy.friend.chat.common.model.chat.MessageEntity
import voxy.friend.chat.common.model.chat.PendingMessageEntity
import voxy.friend.chat.common.model.chat.UserProfileEntity

@Database(
    entities = [
        MessageEntity::class,
        ConversationEntity::class,
        UserProfileEntity::class,
        PendingMessageEntity::class
    ],
    version = 3,
    exportSchema = true
)

expect abstract class VoxyDatabase : RoomDatabase {
    abstract fun messageDao(): MessageDao
    abstract fun conversationDao(): ConversationDao
    abstract fun userProfileDao(): UserProfileDao
}

expect object VoxyDatabaseConstructor : RoomDatabaseConstructor<VoxyDatabase> {
    override fun initialize(): VoxyDatabase
}

expect class DatabaseBuilder {
    fun build(): VoxyDatabase
}