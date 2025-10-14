package voxy.friend.chat.networkinspector

interface NetworkInspector{
    fun logRequest(url: String, method: String, headers: Map<String, String>, body: String?)
    fun logResponse(url: String, statusCode: Int, headers: Map<String, String>, body: String?)
}