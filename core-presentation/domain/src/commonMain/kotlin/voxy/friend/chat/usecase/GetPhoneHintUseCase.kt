package voxy.friend.chat.usecase

import voxy.friend.chat.common.model.PhoneHintResult
import voxy.friend.chat.repository.PhoneHintRepository

class GetPhoneHintUseCase(
    private val phoneHintRepository: PhoneHintRepository
) {
    suspend operator fun invoke(): PhoneHintResult {
        return try {
            phoneHintRepository.requestPhoneHint()
        } catch (e: Exception) {
            PhoneHintResult.Error("Failed to get phone hint: ${e.message}")
        }
    }
}