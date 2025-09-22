package voxy.friend.chat.repository

import voxy.friend.chat.common.model.PhoneHintResult

interface PhoneHintRepository {
    suspend fun requestPhoneHint(): PhoneHintResult
}