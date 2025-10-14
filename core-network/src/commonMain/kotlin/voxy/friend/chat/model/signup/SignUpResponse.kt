package voxy.friend.chat.model.signup

import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    val jwt: String?,
    val rfTkn: String?,
    val userInfo: UserInfo?
)