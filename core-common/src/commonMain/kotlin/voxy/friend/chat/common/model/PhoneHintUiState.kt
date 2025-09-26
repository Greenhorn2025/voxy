package voxy.friend.chat.common.model

data class PhoneHintUiState(
    val isLoading: Boolean = false,
    val phoneNumber: String = "",
    val errorMessage: String? = null,
    val showSuccess: Boolean = false,
    val otpNumber: String = "",
)
