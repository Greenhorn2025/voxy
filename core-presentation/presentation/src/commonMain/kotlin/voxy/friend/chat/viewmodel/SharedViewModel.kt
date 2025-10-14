package voxy.friend.chat.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import voxy.friend.chat.constants.PreferenceKeys.LOGGED_IN_USER_DTO
import voxy.friend.chat.datastore.VoxyDataStoreImpl
import voxy.friend.chat.extension.getObject
import voxy.friend.chat.model.verifyOTP.OTPVerifyResponseDTO
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
            updateBottomSheetState(isVisible = isLoggedIn)
        }
    }

    fun getUserLoggedInDTO() : Flow<OTPVerifyResponseDTO?> {
        return dataStore.getObject<OTPVerifyResponseDTO>(LOGGED_IN_USER_DTO)
            .filterNotNull()
    }


    override fun setDefaultState() = HomeState()
}