package voxy.friend.chat.ai.phonehint

import android.app.Activity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.suspendCancellableCoroutine
import voxy.friend.chat.common.di.ActivityContextProvider
import voxy.friend.chat.common.model.PhoneHintResult
import voxy.friend.chat.source.PhoneHintDataSource
import kotlin.concurrent.Volatile
import kotlin.coroutines.resume

class AndroidPhoneHintDataSource : PhoneHintDataSource {

    @Volatile
    private var isRequestInProgress = false

    companion object{
        private const val TAG = "AndroidPhoneHintDataSourc"

        @Volatile
        private var instance: AndroidPhoneHintDataSource? = null

        fun getInstance(): AndroidPhoneHintDataSource {
            return instance ?: synchronized(this) {
                instance ?: AndroidPhoneHintDataSource().also { instance = it }
            }
        }
        @Volatile
        private var phoneHintLauncher: ActivityResultLauncher<IntentSenderRequest>? = null

        fun setActivityResultLauncher(launcher: ActivityResultLauncher<IntentSenderRequest>) {
            phoneHintLauncher = launcher
        }
    }

    override suspend fun getPhoneHint(): PhoneHintResult = suspendCancellableCoroutine{ continuation ->
        if (isRequestInProgress) {
            continuation.resume(PhoneHintResult.Error("Phone hint request already in progress"))
            return@suspendCancellableCoroutine
        }

        try {
            isRequestInProgress = true
            val request = GetPhoneNumberHintIntentRequest.builder().build()

            val context = ActivityContextProvider.instance.getActivity()
            if (context == null) {
                isRequestInProgress = false
                continuation.resume(PhoneHintResult.Error("No activity context available"))
                return@suspendCancellableCoroutine
            }

            Identity.getSignInClient(context as Activity)
                .getPhoneNumberHintIntent(request)
                .addOnSuccessListener { result ->
                    try {
                        val intentSenderRequest = IntentSenderRequest.Builder(result.intentSender).build()

                        // Store continuation for callback
                        PhoneHintCallback.setContinuation(continuation){
                            isRequestInProgress = false
                        }

                        phoneHintLauncher?.launch(intentSenderRequest)
                            ?: run {
                                isRequestInProgress = false
                                continuation.resume(PhoneHintResult.Error("Activity result launcher not set"))
                            }
                    } catch (e: Exception) {
                        isRequestInProgress = false
                        continuation.resume(PhoneHintResult.Error("Failed to launch phone hint: ${e.message}"))
                    }
                }
                .addOnFailureListener { exception ->
                    isRequestInProgress = false
                    continuation.resume(PhoneHintResult.Error("Failed to get phone hint intent: ${exception.message}"))
                }
        } catch (e: Exception) {
            isRequestInProgress = false
            continuation.resume(PhoneHintResult.Error("Unexpected error: ${e.message}"))
        }
    }
}