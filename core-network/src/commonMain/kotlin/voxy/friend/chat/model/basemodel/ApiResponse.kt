package voxy.friend.chat.model.basemodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    @SerialName("status") val status: Int? = null,
    @SerialName("code") val code: Int? = null,
    @SerialName("message") val message: String?,
    @SerialName("data") val data: T? = null
) {
    val isSuccess: Boolean get() = code in 200..299
}
