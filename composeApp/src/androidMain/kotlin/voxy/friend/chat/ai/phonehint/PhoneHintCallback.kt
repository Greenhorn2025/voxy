package voxy.friend.chat.ai.phonehint

import android.app.Activity
import android.content.Intent
import com.google.android.gms.auth.api.identity.Identity
import voxy.friend.chat.common.model.PhoneHintResult
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

object PhoneHintCallback {
    @Volatile
    private var continuation: Continuation<PhoneHintResult>? = null
    private var onComplete: (() -> Unit)? = null

    fun setContinuation(cont: Continuation<PhoneHintResult>, onCompleteCallback: () -> Unit = {}) {
        continuation = cont
        onComplete = onCompleteCallback
    }

    fun handleActivityResult(resultCode: Int, data: Intent?, activity: Activity) {
        val currentContinuation = continuation
        val currentOnComplete = onComplete

        continuation = null // Clear to prevent memory leaks
        onComplete = null

        when (resultCode) {
            Activity.RESULT_OK -> {
                try {
                    val phoneNumber = Identity.getSignInClient(activity)
                        .getPhoneNumberFromIntent(data)
                    currentContinuation?.resume(PhoneHintResult.Success(phoneNumber))
                } catch (e: Exception) {
                    currentContinuation?.resume(PhoneHintResult.Error("Failed to extract phone number: ${e.message}"))
                }
            }
            Activity.RESULT_CANCELED -> {
                currentContinuation?.resume(PhoneHintResult.Cancelled)
            }
            else -> {
                currentContinuation?.resume(PhoneHintResult.Error("Unknown result code: $resultCode"))
            }
        }
        currentOnComplete?.invoke()
    }
}