package voxy.friend.chat.model.verifyOTP

import kotlinx.serialization.Serializable

@Serializable
data class OTPVerifyResponseDTO(
    val id : Long?,
    val jwt : String?,
    val rft : String?,
    val userInfo : UserInfo?,
)
