package voxy.friend.chat.otpless

sealed class OtplessState{
    data object Idle : OtplessState()
    data object Loading : OtplessState()
    data class InitiateSuccess(val orderId: String, val data: Map<String, Any?>) : OtplessState()
    data class VerifySuccess(val isVerified: Boolean, val data: Map<String, Any?>) : OtplessState()
    data class Error(
        val errorCode: String,
        val errorMessage: String,
        val errorType: VerifyErrorType
    ) : OtplessState()
}