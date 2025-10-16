package voxy.friend.chat.common.database

import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import voxy.friend.chat.common.database.dao.ConversationDao
import voxy.friend.chat.common.database.dao.MessageDao
import voxy.friend.chat.common.database.dao.UserProfileDao


actual abstract class VoxyDatabase : RoomDatabase() {
    actual abstract fun messageDao(): MessageDao
    actual abstract fun conversationDao(): ConversationDao
    actual abstract fun userProfileDao(): UserProfileDao
}

actual object VoxyDatabaseConstructor :
    RoomDatabaseConstructor<VoxyDatabase> {
    actual override fun initialize(): VoxyDatabase {
        throw IllegalStateException("Use getDatabaseBuilder() instead")
    }
}