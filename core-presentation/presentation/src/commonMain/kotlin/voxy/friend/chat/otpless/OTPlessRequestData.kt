package voxy.friend.chat.otpless

data class OTPlessRequestData(
    val phoneNumber: String,
    val countryCode: String,
    val email: String? = null
)
