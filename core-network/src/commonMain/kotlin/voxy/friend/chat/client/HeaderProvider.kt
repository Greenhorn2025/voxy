package voxy.friend.chat.client

interface HeaderProvider {
    suspend fun getAuthToken(): String?
    suspend fun setAuthToken(token: String)
    suspend fun clearAuthToken()
    fun getDeviceId(): String
    fun getHeaders(): Map<String, String>
    fun getContentType(): String
}