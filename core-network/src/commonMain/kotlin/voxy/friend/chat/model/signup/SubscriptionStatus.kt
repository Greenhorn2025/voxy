package voxy.friend.chat.model.signup

import kotlinx.serialization.Serializable

@Serializable
data class SubscriptionStatus(
    val plan: Plan?,
    val subscription: String?
)