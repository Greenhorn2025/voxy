package voxy.friend.chat.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import voxy.friend.chat.auth.TrueCallerRepository
import voxy.friend.chat.auth.TruecallerConfig
import voxy.friend.chat.auth.TruecallerResult
import voxy.friend.chat.client.HeaderProvider
import voxy.friend.chat.common.model.signup.SignUpSideEffect
import voxy.friend.chat.common.model.signup.SignUpUiState
import voxy.friend.chat.constants.PreferenceKeys.SIGNUP_USER_DTO
import voxy.friend.chat.datastore.VoxyDataStoreImpl
import voxy.friend.chat.extension.getObject
import voxy.friend.chat.extension.saveObject
import voxy.friend.chat.model.signup.SignUpResponse
import voxy.friend.chat.network.NetworkMonitor
import voxy.friend.chat.usecase.OnBoardingUseCase

class OnBoardingViewModel(
    networkMonitor: NetworkMonitor,
    private val dataStore: VoxyDataStoreImpl,
    private val onBoardingUseCase: OnBoardingUseCase,
    private val truecallerRepository: TrueCallerRepository,
    private val headerProvider: HeaderProvider
) : BaseViewModel<SignUpUiState, SignUpSideEffect>(networkMonitor, dataStore) {

    init {
        checkTrueCallerAvailability()
    }

    private fun checkTrueCallerAvailability() = viewModelScope.launch {
//        truecallerRepository.registerForActivityResult()
//        delay(2000)
//        authenticateWithTrueCaller()
//        truecallerRepository.isAvailable()
    }

    fun authenticateWithTrueCaller() = viewModelScope.launch {
        viewModelScope.launch {
//            _authState.value = AuthState.Loading

            val config = TruecallerConfig(
                buttonColor = "#1976D2",
                buttonTextColor = "#FFFFFF"
            )

            when (val result = truecallerRepository.authenticate(config)) {
                is TruecallerResult.Success -> {
//                    _authState.value = AuthState.Success(
//                        phoneNumber = result.phoneNumber,
//                        name = "${result.firstName} ${result.lastName}"
//                    )
                }

                is TruecallerResult.Failure -> {
//                    _authState.value = AuthState.Error(result.errorMessage)
                }

                TruecallerResult.Cancelled -> {
//                    _authState.value = AuthState.Cancelled
                }
            }
        }
    }

    fun checkExistingUser() = viewModelScope.launch {
        dataStore.getObject<SignUpResponse>(SIGNUP_USER_DTO).collectLatest { existingUser ->
            if (existingUser == null) {
                signUp()
            }
        }
    }

    private fun signUp() = viewModelScope.launch {
        val result = onBoardingUseCase.invoke()
        result.onSuccess { signUpResponse ->
            dataStore.saveObject(
                SIGNUP_USER_DTO, signUpResponse
            )
            headerProvider.setAuthToken(signUpResponse.jwt.orEmpty())
            println("SignUpResponse: $signUpResponse")
        }.onFailure { exception ->
            println("SignUpResponse: $exception")
        }
    }

    override fun setDefaultState() = SignUpUiState()
}