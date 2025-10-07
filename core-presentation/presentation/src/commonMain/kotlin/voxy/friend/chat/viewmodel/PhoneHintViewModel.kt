package voxy.friend.chat.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import voxy.friend.chat.common.model.PhoneHintResult
import voxy.friend.chat.common.model.PhoneHintSideEffect
import voxy.friend.chat.common.model.PhoneHintUiState
import voxy.friend.chat.network.NetworkMonitor
import voxy.friend.chat.usecase.GetPhoneHintUseCase
import kotlin.coroutines.cancellation.CancellationException


class PhoneHintViewModel(
    private val getPhoneHintUseCase: GetPhoneHintUseCase,
    monitor: NetworkMonitor
) : BaseViewModel<PhoneHintUiState, PhoneHintSideEffect>(monitor) {
    private val _uiState = MutableStateFlow(PhoneHintUiState())
    val uiState: StateFlow<PhoneHintUiState> = _uiState.asStateFlow()

    private var currentHintJob: Job? = null

    fun requestPhoneHint() = viewModelScope.launch {
        if (currentHintJob?.isActive == true) return@launch

        _uiState.value = _uiState.value.copy(isLoading = true)

        try {
            val result = getPhoneHintUseCase.invoke()
            _uiState.value = when (result) {
                is PhoneHintResult.Success -> {
                    _uiState.value.copy(
                        isLoading = false,
                        phoneNumber = result.phoneNumber,
                        showSuccess = true
                    )
                }

                is PhoneHintResult.Error -> {
                     _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }

                is PhoneHintResult.Cancelled -> {
                   _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Phone hint selection was cancelled"
                    )
                }
            }
        } catch (e: CancellationException) {
            // Job was cancelled - do nothing
        } catch (e: Exception) {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                errorMessage = e.message ?: "Unknown error"
            )
        }
    }

    fun clearSuccess() {
        _uiState.value = _uiState.value.copy(
            showSuccess = false,
            phoneNumber = "",
            errorMessage = null
        )
    }

    override fun onCleared() {
        super.onCleared()
        currentHintJob?.cancel()
    }

    fun numberSelected(phoneNumber: String) {
        updateState { it.copy(phoneNumber = phoneNumber) }
    }

    fun updateOTPNumber(otp: String) {
        updateState { it.copy(otpNumber = otp) }
    }

    override fun setDefaultState() = PhoneHintUiState()
}