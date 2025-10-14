package voxy.friend.chat.otpless

data class OTPLessResponseData(
    val statusCode: Int? = null,
    val response: Map<String, Any?> ?= null,
    val otplessState: OtplessState? = null,
    val otpVerified: Boolean = false,
    val dismissDialog: Boolean = false,
)