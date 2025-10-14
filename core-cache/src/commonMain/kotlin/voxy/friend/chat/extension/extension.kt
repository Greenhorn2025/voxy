package voxy.friend.chat.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import voxy.friend.chat.interfaces.VoxyDataStore


suspend inline fun <reified T> VoxyDataStore.saveObject(key: String, value: T) {
    val json = Json { ignoreUnknownKeys = true }
    val jsonString = json.encodeToString(value)
    saveObjectInternal(key, jsonString)
}

inline fun <reified T> VoxyDataStore.getObject(key: String): Flow<T?> {
    val json = Json { ignoreUnknownKeys = true }
    return getObjectInternal(key).map { jsonString ->
        jsonString?.let {
            try {
                json.decodeFromString<T>(it)
            } catch (e: Exception) {
                null
            }
        }
    }
}