package voxy.friend.chat.navigation

import androidx.navigation.NavController

fun NavController.toSignUp(phoneNumber: String = "") = navigate(Destinations.OnBoardingScreen.createRoute(phoneNumber))
fun NavController.toVerify(phoneNumber : String) = navigate(Destinations.VerifyOTPScreen.createRoute(phoneNumber))