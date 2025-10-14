package voxy.friend.chat.repository

import voxy.friend.chat.apiService.OnBoardingService
import voxy.friend.chat.model.basemodel.NetworkResult
import voxy.friend.chat.model.signup.SignUpResponse

class SignUpRepositoryImpl(
    private val onBoardingService: OnBoardingService,
) : SignUpRepository{
    override suspend fun signUp(): Result<SignUpResponse> {
        return when (val res = onBoardingService.getOnBoardingData()) {
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