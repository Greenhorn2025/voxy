package voxy.friend.chat.subscriptionandpaywall

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
    Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            PaywallTopBar()
        }
    ) { paddingValues ->
        MainGraph(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
                .background(MaterialTheme.colorScheme.primary),
            navController = navHostController,
            startDestinations = startDestinations
        )
    }
}


