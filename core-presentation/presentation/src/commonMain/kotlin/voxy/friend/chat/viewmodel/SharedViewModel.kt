package voxy.friend.chat.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
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

    fun updateBottomSheetState(isVisible: Boolean) = viewModelScope.launch {
        withContext(Dispatchers.Main.immediate) {
            updateState { it.copy(showBottomSheet = isVisible) }
        }
    }

    fun checkUserLoggedInState() = viewModelScope.launch {
        dataStore.readIsUserLoggedInState.collectLatest { isLoggedIn ->
            updateBottomSheetState(isLoggedIn)
        }
    }


    override fun setDefaultState() = HomeState()
}