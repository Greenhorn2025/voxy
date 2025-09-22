package voxy.friend.chat.ai.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import voxy.friend.chat.login.SubscriptionPaywallScreen

object VoxyNavGraph : BaseNavGraph {

    sealed class Dest(val route: String) {
        data object Root : Dest("/voxy-root")
        data object Subscription : Dest("/subscription")
    }

    override fun build(
        modifier: Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(route = Dest.Root.route, startDestination = Dest.Subscription.route) {
            composable(route = Dest.Subscription.route) {
                SubscriptionPaywallScreen()
            }
        }
    }
}