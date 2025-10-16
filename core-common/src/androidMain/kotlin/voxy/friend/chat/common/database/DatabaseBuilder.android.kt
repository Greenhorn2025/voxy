package voxy.friend.chat.common.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

actual class DatabaseBuilder {
    private var context: Context? = null

    fun setContext(context: Context): DatabaseBuilder {
        this.context = context
        return this
    }

    actual fun build(): VoxyDatabase {
        val ctx = context ?: throw IllegalStateException("Context not set")
        val dbFile = context?.getDatabasePath("voxy_database.db")

        return Room.databaseBuilder<VoxyDatabase>(
            context = ctx,
            name = dbFile?.absolutePath!!
        )
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .fallbackToDestructiveMigration(true)
            .build()
    }
}

fun getDatabaseBuilder(context: Context): DatabaseBuilder {
    return DatabaseBuilder().setContext(context)
}