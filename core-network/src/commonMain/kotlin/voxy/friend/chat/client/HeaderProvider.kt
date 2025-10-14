package voxy.friend.chat.client

interface HeaderProvider {
    fun getAuthToken(): String?
    fun setAuthToken(token: String)
    fun clearAuthToken()
    fun getDeviceId(): String
    fun getHeaders(): Map<String, String>
    fun getContentType(): String
}