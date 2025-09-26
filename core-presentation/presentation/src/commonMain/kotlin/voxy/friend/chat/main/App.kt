package voxy.friend.chat.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import voxy.friend.chat.subscriptionandpaywall.SubscriptionPaywallScreen
import voxy.friend.chat.navigation.Destinations

@Composable
fun App(navHostController: NavHostController, startDestinations: Destinations) {
    SubscriptionPaywallScreen(navHostController, startDestinations)
}