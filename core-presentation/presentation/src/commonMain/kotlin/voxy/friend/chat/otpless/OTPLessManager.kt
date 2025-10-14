package voxy.friend.chat.otpless

expect class OTPLessManager {
    fun initialize(appId: String)
    fun setCallback(onResponse: (OTPLessResponseData) -> Unit)
    fun handleNewIntent(intent: Any?)
    suspend fun sendOtp(request: OTPlessRequestData)
    suspend fun verifyOtp(otp: String, phoneNumber: String, countryCode: String)
}
