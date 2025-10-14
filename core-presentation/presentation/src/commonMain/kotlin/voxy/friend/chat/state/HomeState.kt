package voxy.friend.chat.state

import voxy.friend.chat.navigation.Destinations

data class HomeState(
    val isLoading: Boolean = false,
    val showBottomSheet : Boolean = true,
    val destinations: Destinations = Destinations.BotChatScreen
)

open class HomeStateSideEffect{

}