package voxy.friend.chat.auth

expect class TrueCallerAuth {
    fun initialize(config: TruecallerConfig)
    fun startAuthentication()
    fun registerForActivityResult()
    fun isUsable(): Boolean
    fun setCallback(callback: TrueCallerCallback)
}

data class TruecallerConfig(
    val buttonColor: String? = null,
    val buttonTextColor: String? = null,
    val loginTextPrefix: LoginTextPrefix = LoginTextPrefix.TO_GET_STARTED,
    val ctaText: CtaText = CtaText.CONTINUE,
    val buttonShape: ButtonShape = ButtonShape.ROUNDED,
    val footerType: FooterType = FooterType.SKIP,
    val consentTitle: ConsentTitle = ConsentTitle.LOG_IN,
    val sdkOption: SdkOption = SdkOption.VERIFY_ONLY_TC_USERS
)

enum class LoginTextPrefix { TO_GET_STARTED, TO_CONTINUE, TO_PLACE_ORDER }
enum class CtaText { CONTINUE, PROCEED, ACCEPT }
enum class ButtonShape { ROUNDED, RECTANGULAR }
enum class FooterType { SKIP, ANOTHER_METHOD }
enum class ConsentTitle { LOG_IN, SIGN_UP, SIGN_IN, GET_STARTED }
enum class SdkOption { VERIFY_ONLY_TC_USERS, VERIFY_ALL_USERS }