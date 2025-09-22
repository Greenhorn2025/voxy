package voxy.friend.chat.repository

import voxy.friend.chat.common.model.PhoneHintResult
import voxy.friend.chat.source.PhoneHintDataSource

class PhoneHintRepositoryImpl(
    private val phoneHintDataSource: PhoneHintDataSource
) : PhoneHintRepository {

    override suspend fun requestPhoneHint(): PhoneHintResult {
        return phoneHintDataSource.getPhoneHint()
    }
}