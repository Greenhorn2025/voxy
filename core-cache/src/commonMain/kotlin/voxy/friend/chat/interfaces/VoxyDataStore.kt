package voxy.friend.chat.interfaces

import kotlinx.coroutines.flow.Flow

interface VoxyDataStore {
    suspend fun saveObjectInternal(key: String, jsonString: String)
    fun getObjectInternal(key: String): Flow<String?>

    suspend fun remove(key: String)
    suspend fun clear()
}