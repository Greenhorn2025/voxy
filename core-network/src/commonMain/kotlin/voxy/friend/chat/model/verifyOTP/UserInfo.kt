package voxy.friend.chat.model.verifyOTP

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val dn: String?,
    val hashKey: String?,
    val subscriptionStatus: String?,
    val uid: Int?,
    val usrSt: String?
)