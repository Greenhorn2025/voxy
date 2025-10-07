package voxy.friend.chat.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import voxy.friend.chat.network.NetworkMonitor
import voxy.friend.chat.state.HomeState
import voxy.friend.chat.state.HomeStateSideEffect

class SharedViewModel(
    monitor: NetworkMonitor
) : BaseViewModel<HomeState, HomeStateSideEffect>(monitor) {

    fun updateBottomSheetState(isVisible: Boolean) = viewModelScope.launch {
        withContext(Dispatchers.Main.immediate) {
            updateState { it.copy(showBottomSheet = isVisible) }
        }
    }

    override fun setDefaultState() = HomeState()
}