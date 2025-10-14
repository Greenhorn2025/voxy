package voxy.friend.chat.networkinspector

class IOSNetworkInspector : NetworkInspector {
    override fun logRequest(url: String, method: String, headers: Map<String, String>, body: String?) {
        println("iOS Request: $method $url")
        println("Headers: $headers")
        body?.let { println("Body: $it") }
    }

    override fun logResponse(url: String, statusCode: Int, headers: Map<String, String>, body: String?) {
        println("iOS Response: $statusCode from $url")
        println("Headers: $headers")
        body?.let { println("Body: $it") }
    }
}