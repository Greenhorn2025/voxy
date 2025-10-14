package voxy.friend.chat.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import voxy.friend.chat.datastore.VoxyDataStoreImpl
import voxy.friend.chat.network.NetworkMonitor
import voxy.friend.chat.state.HomeState
import voxy.friend.chat.state.HomeStateSideEffect

class SharedViewModel(
    monitor: NetworkMonitor,
    private val dataStore: VoxyDataStoreImpl
) : BaseViewModel<HomeState, HomeStateSideEffect>(monitor, dataStore) {

    fun showLoggedInBottomSheetState(isVisible: Boolean) = viewModelScope.launch {
        withContext(Dispatchers.Main.immediate) {
            updateState { it.copy(showBottomSheet = isVisible) }
        }
    }

    override fun setDefaultState() = HomeState()
}