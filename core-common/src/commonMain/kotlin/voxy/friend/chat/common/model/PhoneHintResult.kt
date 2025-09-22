package voxy.friend.chat.common.model

sealed class PhoneHintResult {
    data class Success(val phoneNumber: String) : PhoneHintResult()
    data class Error(val message: String) : PhoneHintResult()
    object Cancelled : PhoneHintResult()
}