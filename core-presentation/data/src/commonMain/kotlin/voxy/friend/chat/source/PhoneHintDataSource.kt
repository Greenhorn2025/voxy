package voxy.friend.chat.source

import voxy.friend.chat.common.model.PhoneHintResult

interface PhoneHintDataSource {
    suspend fun getPhoneHint(): PhoneHintResult
}