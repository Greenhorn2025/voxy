package voxy.friend.chat.auth

sealed class TruecallerResult {
    data class Success(
        val firstName: String,
        val lastName: String,
        val phoneNumber: String,
        val email: String?,
        val accessToken: String
    ) : TruecallerResult()

    data class Failure(
        val errorCode: Int,
        val errorMessage: String
    ) : TruecallerResult()

    object Cancelled : TruecallerResult()
}