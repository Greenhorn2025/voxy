package voxy.friend.chat.auth

import android.content.Intent
import android.graphics.Color
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.truecaller.android.sdk.oAuth.CodeVerifierUtil
import com.truecaller.android.sdk.oAuth.TcOAuthCallback
import com.truecaller.android.sdk.oAuth.TcOAuthData
import com.truecaller.android.sdk.oAuth.TcOAuthError
import com.truecaller.android.sdk.oAuth.TcSdk
import com.truecaller.android.sdk.oAuth.TcSdkOptions
import java.math.BigInteger
import java.security.SecureRandom

private const val TAG = "TruecallerAuth.android"
actual class TrueCallerAuth(private val activity: FragmentActivity) {

    private var callback: TrueCallerCallback? = null
    private var launcher: ActivityResultLauncher<Intent>? = null
    private var isInitialized = false

    private val tcOAuthCallback = object : TcOAuthCallback {
        override fun onSuccess(tcOAuthData: TcOAuthData) {
            Log.d(TAG, tcOAuthData.authorizationCode)
//            callback?.onResult(
//                TruecallerResult.Success(
//                    firstName = tcOAuthData.firstName ?: "",
//                    lastName = tcOAuthData.lastName ?: "",
//                    phoneNumber = tcOAuthData.phoneNumber ?: "",
//                    email = tcOAuthData.email,
//                    accessToken = tcOAuthData.accessToken ?: ""
//                )
//            )
        }

        override fun onFailure(tcOAuthError: TcOAuthError) {
            callback?.onResult(
                TruecallerResult.Failure(
                    errorCode = tcOAuthError.errorCode,
                    errorMessage = tcOAuthError.errorMessage
                )
            )
        }

        override fun onVerificationRequired(tcOAuthError: TcOAuthError?) {
            Log.d(TAG, " ${tcOAuthError?.errorMessage}")
        }
    }

    actual fun registerForActivityResult(){
        // Register activity result launcher
        launcher = activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->

            when (result.resultCode) {
                AppCompatActivity.RESULT_OK -> {
                    TcSdk.getInstance().onActivityResultObtained(
                        activity,
                        result.resultCode,
                        result.data
                    )
                }
                AppCompatActivity.RESULT_CANCELED -> {
                    Log.w(TAG, "User cancelled the authentication")
                    callback?.onResult(TruecallerResult.Cancelled)
                }
                else -> {
                    Log.e(TAG, "Unknown result code: ${result.resultCode}")
                    callback?.onResult(
                        TruecallerResult.Failure(
                            errorCode = -3,
                            errorMessage = "Unknown error occurred"
                        )
                    )
                }
            }
        }
    }

    actual fun initialize(config: TruecallerConfig) {
        // Build SDK options
        val builder = TcSdkOptions.Builder(activity, tcOAuthCallback)

        // Apply configurations
        config.buttonColor?.let {
            builder.buttonColor(Color.parseColor(it))
        }
        config.buttonTextColor?.let {
            builder.buttonTextColor(Color.parseColor(it))
        }

        builder.loginTextPrefix(mapLoginTextPrefix(config.loginTextPrefix))
        builder.ctaText(mapCtaText(config.ctaText))
        builder.buttonShapeOptions(mapButtonShape(config.buttonShape))
        builder.footerType(mapFooterType(config.footerType))
        builder.consentTitleOption(mapConsentTitle(config.consentTitle))
        builder.sdkOptions(mapSdkOption(config.sdkOption))

        val tcSdkOptions = builder.build()

        // Initialize SDK
        TcSdk.init(tcSdkOptions)
        isInitialized = true

        setupOAuthParameters()
    }

    private fun setupOAuthParameters() {
        // Set OAuth state for security
        val state = BigInteger(130, SecureRandom()).toString(32)
        TcSdk.getInstance().setOAuthState(state)

        // Generate and set code challenge
        val codeChallenge = generateCodeChallenge()
        TcSdk.getInstance().setCodeChallenge(codeChallenge)
    }

    private fun generateCodeChallenge(): String {
        return CodeVerifierUtil.generateRandomCodeVerifier()
    }

    actual fun startAuthentication() {
        if (!isInitialized) {
            callback?.onResult(
                TruecallerResult.Failure(-1, "SDK not initialized")
            )
            return
        }

        if (!isUsable()) {
            callback?.onResult(
                TruecallerResult.Failure(-2, "Truecaller not usable")
            )
            return
        }
        setupOAuthParameters()

        launcher?.let {
            TcSdk.getInstance().setOAuthScopes(arrayOf("profile", "phone", "email"))
            TcSdk.getInstance().getAuthorizationCode(activity, it)
        }
    }

    actual fun isUsable(): Boolean {
        return if (isInitialized) {
            TcSdk.getInstance().isOAuthFlowUsable
        } else {
            false
        }
    }

    actual fun setCallback(callback: TrueCallerCallback) {
        this.callback = callback
    }

    // Mapping functions
    private fun mapLoginTextPrefix(prefix: LoginTextPrefix): Int = when (prefix) {
        LoginTextPrefix.TO_GET_STARTED -> TcSdkOptions.LOGIN_TEXT_PREFIX_TO_GET_STARTED
        LoginTextPrefix.TO_CONTINUE -> TcSdkOptions.LOGIN_TEXT_PREFIX_TO_CONTINUE
        LoginTextPrefix.TO_PLACE_ORDER -> TcSdkOptions.LOGIN_TEXT_PREFIX_TO_PLACE_ORDER
    }

    private fun mapCtaText(cta: CtaText): Int = when (cta) {
        CtaText.CONTINUE -> TcSdkOptions.CTA_TEXT_CONTINUE
        CtaText.PROCEED -> TcSdkOptions.CTA_TEXT_PROCEED
        CtaText.ACCEPT -> TcSdkOptions.CTA_TEXT_ACCEPT
    }

    private fun mapButtonShape(shape: ButtonShape): Int = when (shape) {
        ButtonShape.ROUNDED -> TcSdkOptions.BUTTON_SHAPE_ROUNDED
        ButtonShape.RECTANGULAR -> TcSdkOptions.BUTTON_SHAPE_RECTANGLE
    }

    private fun mapFooterType(footer: FooterType): Int = when (footer) {
        FooterType.SKIP -> TcSdkOptions.FOOTER_TYPE_SKIP
        FooterType.ANOTHER_METHOD -> TcSdkOptions.FOOTER_TYPE_ANOTHER_METHOD
    }

    private fun mapConsentTitle(title: ConsentTitle): Int = when (title) {
        ConsentTitle.LOG_IN -> TcSdkOptions.LOGIN_TEXT_PREFIX_TO_GET_STARTED
        ConsentTitle.SIGN_UP -> TcSdkOptions.SDK_CONSENT_HEADING_SIGN_UP_WITH
        ConsentTitle.SIGN_IN -> TcSdkOptions.SDK_CONSENT_HEADING_SIGN_IN_TO
        ConsentTitle.GET_STARTED -> TcSdkOptions.LOGIN_TEXT_PREFIX_TO_GET_STARTED
    }

    private fun mapSdkOption(option: SdkOption): Int = when (option) {
        SdkOption.VERIFY_ONLY_TC_USERS -> TcSdkOptions.OPTION_VERIFY_ONLY_TC_USERS
        SdkOption.VERIFY_ALL_USERS -> TcSdkOptions.OPTION_VERIFY_ALL_USERS
    }
}
