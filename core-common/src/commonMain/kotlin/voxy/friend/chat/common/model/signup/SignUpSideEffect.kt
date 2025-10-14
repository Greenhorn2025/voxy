package voxy.friend.chat.common.model.signup

sealed class SignUpSideEffect {
    data class ShowErrorMessage(val message: String) : SignUpSideEffect()
    object Idle : SignUpSideEffect()
    object Loading : SignUpSideEffect()
    data class Success(val phoneNumber: String, val name: String) : SignUpSideEffect()
    data class Error(val message: String) : SignUpSideEffect()
    object Cancelled : SignUpSideEffect()
}