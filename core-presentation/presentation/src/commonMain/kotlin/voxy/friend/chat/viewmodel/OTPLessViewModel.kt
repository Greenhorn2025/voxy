package voxy.friend.chat.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import voxy.friend.chat.client.HeaderProvider
import voxy.friend.chat.constants.PreferenceKeys.LOGGED_IN_USER_DTO
import voxy.friend.chat.datastore.VoxyDataStoreImpl
import voxy.friend.chat.extension.getMapValue
import voxy.friend.chat.extension.getStringValue
import voxy.friend.chat.extension.saveObject
import voxy.friend.chat.network.NetworkMonitor
import voxy.friend.chat.otpless.OTPLessManager
import voxy.friend.chat.otpless.OTPLessResponseData
import voxy.friend.chat.otpless.OTPlessRequestData
import voxy.friend.chat.otpless.OtplessState
import voxy.friend.chat.otpless.VerifyErrorType
import voxy.friend.chat.usecase.OnBoardingUseCase

class OTPLessViewModel(
    networkMonitor: NetworkMonitor,
    private val dataStore: VoxyDataStoreImpl,
    private val otplessManager: OTPLessManager,
    private val onBoardingUseCase: OnBoardingUseCase,
    private val headerProvider: HeaderProvider
) : BaseViewModel<OTPLessResponseData, OtplessState>(networkMonitor, dataStore) {

    private var currentRequestData: OTPlessRequestData? = null

    init {
        otplessManager.setCallback {
            handleOTPLessResponse(it)
        }
    }

    fun sendOtp(phoneNumber: String, countryCode: String, email: String? = null) {
        viewModelScope.launch {
            try {
                currentRequestData = OTPlessRequestData(phoneNumber, countryCode, email)
                otplessManager.sendOtp(currentRequestData!!)
            } catch (e: Exception) {
                updateState {
                    it.copy(
                        otplessState = OtplessState.Error(
                            errorCode = "CLIENT_ERROR",
                            errorMessage = e.message ?: "Failed to send OTP",
                            errorType = VerifyErrorType.UNKNOWN
                        )
                    )
                }
            }
        }
    }

    fun verifyOTPByOTPLess(otp: String, phoneNumber: String, countryCode: String, email: String? = null) {
        viewModelScope.launch {
            try {
                setLoading()
                currentRequestData = OTPlessRequestData(phoneNumber, countryCode, email)
                currentRequestData?.let { data ->
                    otplessManager.verifyOtp(otp, data.phoneNumber, data.countryCode)
                } ?: run {
                    updateState {
                        it.copy(
                            otplessState = OtplessState.Error(
                                errorCode = "CLIENT_ERROR",
                                errorMessage = "No phone number found. Please send OTP first.",
                                errorType = VerifyErrorType.UNKNOWN
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                updateState {
                    it.copy(
                        otplessState = OtplessState.Error(
                            errorCode = "CLIENT_ERROR",
                            errorMessage = "No phone number found. Please send OTP first.",
                            errorType = VerifyErrorType.UNKNOWN
                        )
                    )
                }
            }
        }
    }

    fun verifyOTPFromBE(token: String) = viewModelScope.launch{
        onBoardingUseCase.verifyOTP(token).onSuccess {
            dataStore.saveObject(LOGGED_IN_USER_DTO, it)
            dataStore.saveIsUserLoggedIn(true)
            headerProvider.setAuthToken(it.jwt.orEmpty())
            updateState { state -> state.copy(otpVerified = true)}
        }.onFailure {

        }
    }

    fun handleOTPLessResponse(response: OTPLessResponseData) {
        when (response.statusCode) {
            200, 201 -> handleSuccess(response)
            else -> handleError(response)
        }
    }

    private fun handleSuccess(response: OTPLessResponseData) {
        val data = response.response ?: emptyMap()

        when {
            data.containsKey("data") -> {
                val isVerified = data.getMapValue("data")?.getStringValue("status") == "SUCCESS"
                updateState { it.copy(otplessState = OtplessState.VerifySuccess(isVerified, data)) }
            }
            else -> {
                updateState { it.copy(otplessState = OtplessState.InitiateSuccess("", data)) }
            }
        }
    }


    private fun handleError(response: OTPLessResponseData) {
        val errorCode = response.response?.get("errorCode") as? String ?: "UNKNOWN"
        val errorMessage = response.response?.get("errorMessage") as? String
            ?: getErrorMessage(errorCode)

        when (errorCode) {
            "EMPTY_OTP" -> {
                updateState {
                    it.copy(
                        otplessState = OtplessState.Error(
                            errorCode,
                            errorMessage,
                            VerifyErrorType.EMPTY_OTP
                        )
                    )
                }
                return
            }

            "ALREADY_VERIFIED" -> {
                updateState {
                    it.copy(
                        otplessState = OtplessState.Error(
                            errorCode,
                            errorMessage,
                            VerifyErrorType.ALREADY_VERIFIED
                        )
                    )
                }
                return
            }

            "INCORRECT_OTP" -> {
                updateState {
                    it.copy(
                        otplessState = OtplessState.Error(
                            errorCode,
                            errorMessage,
                            VerifyErrorType.INCORRECT_OTP
                        )
                    )
                }
                return
            }

            "INVALID_REQUEST" -> {
                updateState {
                    it.copy(
                        otplessState = OtplessState.Error(
                            errorCode,
                            errorMessage,
                            VerifyErrorType.INVALID_REQUEST
                        )
                    )
                }
                return
            }
        }
    }

    private fun getErrorMessage(errorCode: String): String {
        return when (errorCode) {
            "7101" -> "Invalid parameters or missing parameters"
            "7102" -> "Invalid phone number"
            "7103" -> "Invalid phone number delivery channel"
            "7104" -> "Invalid email"
            "7105" -> "Invalid email channel"
            "7106" -> "Invalid phone number or email"
            "7113" -> "Invalid expiry"
            "7116" -> "OTP length is invalid (4 or 6 only allowed)"
            "7121" -> "Invalid app hash"
            "7112" -> "Empty OTP"
            "7115" -> "OTP is already verified"
            "7118" -> "Incorrect OTP"
            "7303" -> "OTP expired"
            "4000" -> "Invalid request"
            "4003" -> "Incorrect request channel"
            "401" -> "Unauthorized request"
            "7025" -> "Country not enabled"
            "7020", "7022", "7023", "7024" -> "Too many requests. Please try again later"
            "9100", "9104", "9103" -> "Network connectivity error"
            else -> "An unknown error occurred"
        }
    }

    fun setLoading() {
        updateState { it.copy(otplessState = OtplessState.Loading) }
    }

    fun reset() {
        updateState { it.copy(otplessState = OtplessState.Idle) }
    }

    fun dismissOtpScreen() {
        updateState { it.copy(dismissDialog = true) }
    }

    override fun setDefaultState() = OTPLessResponseData()
}