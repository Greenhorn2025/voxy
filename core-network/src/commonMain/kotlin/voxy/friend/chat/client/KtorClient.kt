package voxy.friend.chat.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import voxy.friend.chat.networkinspector.NetworkInspector

class KtorClient(
    private val inspector: NetworkInspector? = null,
    private val enableLogging: Boolean = false,
    private val headerProvider: HeaderProvider,
) {

    companion object {
        private const val BASE_URL = "https://api-dev.voxy.in/"
    }

    fun getInstance(): HttpClient = HttpClient {

        install(DefaultRequest) {
            url(BASE_URL)
            contentType(ContentType.Application.Json)
            headerProvider.getHeaders().forEach { (key, value) ->
                header(key, value)
            }
        }

        if (enableLogging) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Network-Log: $message")
                    }
                }
                level = LogLevel.ALL
            }
        }

        install(ContentNegotiation) {
            json(json = Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        install(HttpTimeout) {
            socketTimeoutMillis = 30000
            connectTimeoutMillis = 30000
            requestTimeoutMillis = 30000
        }

        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 3)
            exponentialDelay()
        }

        install(ResponseObserver) {
            onResponse { response ->
                inspector?.logResponse(
                    url = response.call.request.url.toString(),
                    statusCode = response.status.value,
                    headers = response.headers.entries()
                        .associate { it.key to it.value.joinToString() },
                    body = response.bodyAsText()
                )
            }
        }
    }
}