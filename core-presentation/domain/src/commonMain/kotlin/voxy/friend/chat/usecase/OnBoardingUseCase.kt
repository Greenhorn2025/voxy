package voxy.friend.chat.usecase

import voxy.friend.chat.repository.SignUpRepository
import voxy.friend.chat.repository.VerifyOTPRepository

class OnBoardingUseCase(
    private val signUpRepository: SignUpRepository,
    private val otpRepository: VerifyOTPRepository
) {
    suspend operator fun invoke() = signUpRepository.signUp()
    suspend fun verifyOTP(token: String) = otpRepository.verifyOTP(token)
}