package voxy.friend.chat.navigation

sealed class Destinations(val route: String) {
    data object OnBoardingScreen : Destinations(route = "on_boarding_screen?phoneNumber={phoneNumber}"){
        fun createRoute(phoneNumber: String = ""): String {
            return if (phoneNumber.isNotEmpty()) {
                "on_boarding_screen?phoneNumber=$phoneNumber"
            } else {
                "on_boarding_screen"
            }
        }
    }
    data object VerifyOTPScreen : Destinations(route = "verify_otp_screen/{phoneNumber}") {
        fun createRoute(phoneNumber: String): String {
            return "verify_otp_screen/$phoneNumber"
        }
    }
    data object SubscriptionScreen : Destinations(route = "subscription_screen")
}