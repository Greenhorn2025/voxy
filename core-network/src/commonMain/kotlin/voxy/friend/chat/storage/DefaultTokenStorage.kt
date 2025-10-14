package voxy.friend.chat.storage

expect class SecureStorage {
    fun save(key: String, value: String)
    fun get(key: String): String?
    fun remove(key: String)
    fun clear()
}

class DefaultTokenStorage(private val secureStorage: SecureStorage) : TokenStorage {
    companion object {
        private const val KEY_AUTH_TOKEN = "auth_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
    }

    override fun saveToken(token: String) {
        secureStorage.save(KEY_AUTH_TOKEN, token)
    }

    override fun getToken(): String? {
        return secureStorage.get(KEY_AUTH_TOKEN)
    }

    override fun clearToken() {
        secureStorage.remove(KEY_AUTH_TOKEN)
    }

    override fun saveRefreshToken(token: String) {
        secureStorage.save(KEY_REFRESH_TOKEN, token)
    }

    override fun getRefreshToken(): String? {
        return secureStorage.get(KEY_REFRESH_TOKEN)
    }

    override fun clearRefreshToken() {
        secureStorage.remove(KEY_REFRESH_TOKEN)
    }
}