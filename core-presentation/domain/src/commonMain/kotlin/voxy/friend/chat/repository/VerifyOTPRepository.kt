package voxy.friend.chat.repository

import voxy.friend.chat.model.verifyOTP.OTPVerifyResponseDTO

interface VerifyOTPRepository {
    suspend fun verifyOTP(token: String) : Result<OTPVerifyResponseDTO>
}