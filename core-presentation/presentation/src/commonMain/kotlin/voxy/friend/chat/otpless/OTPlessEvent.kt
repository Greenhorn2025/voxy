package voxy.friend.chat.otpless

sealed interface OTPlessEvent {
    data object SdkReady : OTPlessEvent
    data object NavigateToOtpScreen : OTPlessEvent
    data object ShowSilentAuthLoading : OTPlessEvent
    data class AutoFillOtp(val otp: String) : OTPlessEvent
    data class AuthenticationSuccess(val token: String?, val idToken: String?) : OTPlessEvent
    data object AuthenticationFailed : OTPlessEvent
    data class DeliverySuccess(val authType: String, val channel: String) : OTPlessEvent
    data class FallbackChannel(val channel: String) : OTPlessEvent
    data class Error(val message: String) : OTPlessEvent
}