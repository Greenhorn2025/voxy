package voxy.friend.chat.model.signup

import kotlinx.serialization.Serializable

@Serializable
data class Plan(
    val currencyDisplay: String?,
    val trialDisplayAmount: Double?
)