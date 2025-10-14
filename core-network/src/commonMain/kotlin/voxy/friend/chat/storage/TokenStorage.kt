package voxy.friend.chat.storage

interface TokenStorage {
    fun saveToken(token: String)
    fun getToken(): String?
    fun clearToken()
    fun saveRefreshToken(token: String)
    fun getRefreshToken(): String?
    fun clearRefreshToken()
}