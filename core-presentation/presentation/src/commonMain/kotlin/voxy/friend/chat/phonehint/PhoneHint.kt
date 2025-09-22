package voxy.friend.chat.phonehint

interface PhoneHint {
    suspend fun requestPhoneNumber(): String?
}