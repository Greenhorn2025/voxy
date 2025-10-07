package voxy.friend.chat.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import voxy.friend.chat.chat.ChatBotScreen
import voxy.friend.chat.subscriptionandpaywall.PaywallScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainGraph(
    modifier: Modifier,
    navController: NavHostController,
    startDestinations: Destinations
) {
    NavHost(
        navController = navController,
        startDestination = startDestinations.route,
    ) {
        composable(route = Destinations.BotChatScreen.route) {
            ChatBotScreen(modifier)
        }
        composable(route = Destinations.SubscriptionScreen.route) {
            PaywallScreen(modifier)
        }
    }
}
