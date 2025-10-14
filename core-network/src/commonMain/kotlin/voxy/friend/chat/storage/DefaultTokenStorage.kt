package voxy.friend.chat.storage

import kotlinx.coroutines.flow.Flow

expect class SecureStorage {
    suspend fun save(key: String, value: String)
    suspend fun get(key: String): String?
    suspend fun remove(key: String)
    suspend fun clear()
    fun getFlow(key: String): Flow<String?>
}

class DefaultTokenStorage(private val secureStorage: SecureStorage) : TokenStorage {
    companion object {
        private const val KEY_AUTH_TOKEN = "auth_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
    }

    override suspend fun saveToken(token: String) {
        secureStorage.save(KEY_AUTH_TOKEN, token)
    }

    override suspend fun getToken(): String? {
        return secureStorage.get(KEY_AUTH_TOKEN)
    }

    override suspend fun clearToken() {
        secureStorage.remove(KEY_AUTH_TOKEN)
    }

    override suspend fun saveRefreshToken(token: String) {
        secureStorage.save(KEY_REFRESH_TOKEN, token)
    }

    override suspend fun getRefreshToken(): String? {
        return secureStorage.get(KEY_REFRESH_TOKEN)
    }

    override suspend fun clearRefreshToken() {
        secureStorage.remove(KEY_REFRESH_TOKEN)
    }
}