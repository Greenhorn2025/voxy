package voxy.friend.chat.otpless

enum class VerifyErrorType {
    EMPTY_OTP,
    ALREADY_VERIFIED,
    INCORRECT_OTP,
    OTP_EXPIRED,
    INVALID_REQUEST,
    NETWORK_ERROR,
    UNKNOWN
}