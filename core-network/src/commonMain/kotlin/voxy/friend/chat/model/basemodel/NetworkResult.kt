package voxy.friend.chat.model.basemodel

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class ApiError(
        val code: Int? = null,
        val status: Int? = null,
        val message: String? = null,
        val errorBody: String? = null // optional: raw server body for debugging
    ) : NetworkResult<Nothing>()
    data class NetworkFailure(val throwable: Throwable) : NetworkResult<Nothing>()
}

inline fun <reified T> ApiResponse<T>.toResult(): NetworkResult<T> =
    if (isSuccess && data != null) {
        NetworkResult.Success(data)
    } else {
        NetworkResult.ApiError(code, status, message)
    }