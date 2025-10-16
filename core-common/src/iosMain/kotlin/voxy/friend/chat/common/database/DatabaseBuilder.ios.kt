package voxy.friend.chat.common.database

actual class DatabaseBuilder {
    actual fun build(): VoxyDatabase {
        return VoxyDatabaseConstructor.initialize()
    }
}