package voxy.friend.chat.auth

import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.suspendCancellableCoroutine
import voxy.friend.chat.common.di.ActivityContextProvider

class AndroidTrueCallerRepository(
    private val activity: ActivityContextProvider
) : TrueCallerRepository {
    private val truecallerAuth = TrueCallerAuth(activity.getActivity() as FragmentActivity)

    override suspend fun authenticate(config: TruecallerConfig): TruecallerResult {
        return suspendCancellableCoroutine { continuation ->
            truecallerAuth.initialize(config)
            truecallerAuth.setCallback(object : TrueCallerCallback {
                override fun onResult(result: TruecallerResult) {
                    continuation.resume(result) { cause, _, _ ->

                    }
                }
            })
            truecallerAuth.startAuthentication()
        }
    }

    override suspend fun registerForActivityResult() {
        truecallerAuth.registerForActivityResult()
        authenticate(
            TruecallerConfig(
                buttonColor = "#1976D2",
                buttonTextColor = "#FFFFFF"
            )
        )
    }

    override fun isAvailable(): Boolean = truecallerAuth.isUsable()
}