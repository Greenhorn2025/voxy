package voxy.friend.chat.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

actual class HttpClientFactory {
    actual fun create(): HttpClient {
        return createHttpClient(HttpClient(Darwin) {
            engine {
                configureRequest {
                    setAllowsCellularAccess(true)
                }
            }
        })
    }
}