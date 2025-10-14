package voxy.friend.chat.repository

import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import voxy.friend.chat.apiService.OnBoardingService
import voxy.friend.chat.constants.PreferenceKeys.SIGNUP_USER_DTO
import voxy.friend.chat.datastore.VoxyDataStoreImpl
import voxy.friend.chat.extension.getObject
import voxy.friend.chat.model.basemodel.NetworkResult
import voxy.friend.chat.model.signup.SignUpResponse

class SignUpRepositoryImpl(
    private val onBoardingService: OnBoardingService,
    private val sessionManager: VoxyDataStoreImpl
) : SignUpRepository{
    override suspend fun signUp(): Result<SignUpResponse> {
        val hasSession = sessionManager.hasValidSession(
            key = stringPreferencesKey(name = SIGNUP_USER_DTO),
            validator = { token ->  !token.isNullOrBlank() }
        )
        if (hasSession) {
            val existingSession = sessionManager.getObject<SignUpResponse>(SIGNUP_USER_DTO).firstOrNull()
            if (existingSession != null) {
                return Result.success(existingSession)
            }
        }
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