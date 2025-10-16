package voxy.friend.chat.common.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSHomeDirectory
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
        val dbFile = "${NSHomeDirectory()}/voxy_database.db"

        return Room.databaseBuilder<VoxyDatabase>(
            name = dbFile
        )
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .fallbackToDestructiveMigration(true)
            .build()
    }
}