package voxy.friend.chat.otpless

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.otpless.v2.android.sdk.dto.OtplessRequest
import com.otpless.v2.android.sdk.dto.OtplessResponse
import com.otpless.v2.android.sdk.main.OtplessSDK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import voxy.friend.chat.common.di.ActivityContextProvider
import voxy.friend.chat.extension.toMap

private const val TAG = "OTPlessManager"
actual class OTPLessManager(private val contextProvider: ActivityContextProvider) {
    private var responseCallback: ((OTPLessResponseData) -> Unit)? = null

    actual fun initialize(appId: String) {
        val activity = contextProvider.getActivity() as? Activity
            ?: throw IllegalStateException("Activity context is required to initialize OTPlessSDK")
        OtplessSDK.initialize(appId = appId, activity = activity)
        OtplessSDK.setResponseCallback(::onOtplessResponse)
    }

    actual fun setCallback(onResponse: (OTPLessResponseData) -> Unit) {
        this.responseCallback = onResponse
    }

    actual fun handleNewIntent(intent: Any?) {
        if (intent is Intent) {
            CoroutineScope(Dispatchers.Main.immediate).launch {
                OtplessSDK.onNewIntent(intent)
            }
        }
    }

    actual suspend fun sendOtp(request: OTPlessRequestData) {
        suspendCancellableCoroutine { continuation ->
            val otplessRequest = OtplessRequest()
            otplessRequest.setPhoneNumber(
                number = request.phoneNumber,
                countryCode = request.countryCode
            )
            CoroutineScope(Dispatchers.Main.immediate).launch {
                OtplessSDK.start(
                    request = otplessRequest,
                    callback = ::onOtplessResponse
                )
            }

            continuation.resume(Unit) { cause, _, _ ->  }
        }
    }

    actual suspend fun verifyOtp(otp: String, phoneNumber: String, countryCode: String) {
        suspendCancellableCoroutine { continuation ->
            val otplessRequest = OtplessRequest()
            otplessRequest.setPhoneNumber(
                number = phoneNumber,
                countryCode = countryCode
            )
            otplessRequest.setOtp(otp)

            CoroutineScope(Dispatchers.Main.immediate).launch {
                OtplessSDK.start(
                    request = otplessRequest,
                    callback = ::onOtplessResponse
                )
            }

            continuation.resume(Unit) { cause, _, _ -> }
        }
    }

    private fun onOtplessResponse(response: OtplessResponse) {
        val responseData = OTPLessResponseData(
            statusCode = response.statusCode ?: 0,
            response = response.response?.toMap()
        )
        Log.i(TAG, "onOtplessResponse: $responseCallback ${response}")
        responseCallback?.invoke(responseData)
    }
}