package voxy.friend.chat.state

import voxy.friend.chat.navigation.Destinations

data class HomeState(
    val isLoading: Boolean = false,
    val showBottomSheet : Boolean = false,
    val destinations: Destinations = Destinations.SubscriptionScreen
)

open class HomeStateSideEffect{

}