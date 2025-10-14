package voxy.friend.chat.networkinspector

import android.content.Context

class AndroidNetworkInspector(context: Context) : NetworkInspector {
    override fun logRequest(
        url: String,
        method: String,
        headers: Map<String, String>,
        body: String?
    ) {
        println("Android Request: $method $url $body $headers")
    }

    override fun logResponse(
        url: String,
        statusCode: Int,
        headers: Map<String, String>,
        body: String?
    ) {
        println("Android Response: $statusCode $url $body")
    }
}