package voxy.friend.chat.repository

import voxy.friend.chat.apiService.OTPVerifyService
import voxy.friend.chat.model.basemodel.NetworkResult
import voxy.friend.chat.model.verifyOTP.OTPVerifyResponseDTO

class VerifyOTPRepositoryImpl(
    private val otpVerifyService: OTPVerifyService
) : VerifyOTPRepository {
    override suspend fun verifyOTP(token: String): Result<OTPVerifyResponseDTO> {
        return when (val res = otpVerifyService.getVerifyOTP(token)) {
            is NetworkResult.Success -> Result.success(res.data)
            is NetworkResult.ApiError -> {
                // Wrap server-declared error (non-2xx or JSON code != 2xx)
                Result.failure(IllegalStateException("API ${res.code}: ${res.message}"))
            }
            is NetworkResult.NetworkFailure -> {
                // Network/serialization/timeouts
                Result.failure(res.throwable)
            }
        }
    }
}