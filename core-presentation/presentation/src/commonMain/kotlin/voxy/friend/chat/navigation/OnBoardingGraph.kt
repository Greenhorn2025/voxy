package voxy.friend.chat.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.github.aakira.napier.Napier.e
import network.chaintech.sdpcomposemultiplatform.sdp
import voxy.friend.chat.onboarding.AnimatedBottomSheet
import voxy.friend.chat.onboarding.AuthenticationOTPVerifyScreen
import voxy.friend.chat.onboarding.AuthenticationScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingGraph(onDismiss: () -> Unit = {}) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.OnBoardingScreen.route,
    ) {
        composable(route = Destinations.OnBoardingScreen.route, arguments = listOf(
                navArgument("phoneNumber") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )) { backStackEntry ->
            val prefilledPhoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            AnimatedBottomSheet(
                value = true,
                tonalElevation = 10.sdp,
                onDismissRequest = { /*TODO*/ },
                containerColor = MaterialTheme.colorScheme.primary,
                borderColor = MaterialTheme.colorScheme.outline
            ) {
                AuthenticationScreen(
                    modifier = Modifier.fillMaxSize(),
                    initialPhoneNumber = prefilledPhoneNumber,
                    onGetOtpClick = navController::toVerify,
                    onGoogleSignInClick = {})
            }
        }


        composable(
            route = Destinations.VerifyOTPScreen.route,
            arguments = listOf(navArgument("phoneNumber") { type = NavType.StringType })
        ) { backStackEntry ->
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            AuthenticationOTPVerifyScreen(
                phoneNumber = phoneNumber,
                onEditClick = navController::toSignUp,
                onDismiss = {
                    onDismiss.invoke()
                }
            )
        }
    }
}