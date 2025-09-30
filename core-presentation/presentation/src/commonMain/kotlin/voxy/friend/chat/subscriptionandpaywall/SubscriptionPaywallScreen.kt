package voxy.friend.chat.subscriptionandpaywall

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import voxy.friend.chat.navigation.Destinations
import voxy.friend.chat.navigation.MainGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscriptionPaywallScreen(
    navHostController: NavHostController,
    startDestinations: Destinations
) {
    MainGraph(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        navController = navHostController,
        startDestinations = startDestinations
    )
}


