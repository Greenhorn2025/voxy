package voxy.friend.chat.model.signup

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val dn: String?,
    val hashKey: String?,
    val subscriptionStatus: SubscriptionStatus?,
    val uid: Int?,
    val usrSt: String?
)