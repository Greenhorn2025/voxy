package voxy.friend.chat.model.basemodel

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText

suspend inline fun <reified T> safeRequest(
    client: HttpClient,
    crossinline block: suspend HttpClient.() -> HttpResponse
): NetworkResult<T> {
    return try {
        val httpResponse = client.block()

        // Try to parse your JSON wrapper
        val body: ApiResponse<T> = httpResponse.body()

        // Map wrapper to result (your API uses "code"/"message" inside JSON)
        body.toResult()

    } catch (e: ClientRequestException) {
        // 4xx with body
        val status = e.response.status.value
        val text = runCatching { e.response.bodyAsText() }.getOrNull()
        NetworkResult.ApiError(code = status, message = "Client error $status", status = status)

    } catch (e: ServerResponseException) {
        // 5xx with body
        val status = e.response.status.value
        val text = runCatching { e.response.bodyAsText() }.getOrNull()
        NetworkResult.ApiError(code = status, message = "Server error $status", status = status)

    } catch (e: RedirectResponseException) {
        val status = e.response.status.value
        NetworkResult.ApiError(code = status, message ="Redirection $status", status = status)

    } catch (e: Throwable) {
        // DNS, timeouts, serialization, cancellation (handle as you prefer)
        NetworkResult.NetworkFailure(e)
    }
}