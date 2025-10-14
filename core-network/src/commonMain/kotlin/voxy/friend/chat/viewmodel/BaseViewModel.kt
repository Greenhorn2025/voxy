package voxy.friend.chat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import voxy.friend.chat.constants.PreferenceKeys.LOGGED_IN_USER_DTO
import voxy.friend.chat.datastore.VoxyDataStoreImpl
import voxy.friend.chat.extension.getObject
import voxy.friend.chat.model.verifyOTP.OTPVerifyResponseDTO
import voxy.friend.chat.network.NetworkInfo
import voxy.friend.chat.network.NetworkMonitor
import voxy.friend.chat.network.NetworkState

abstract class BaseViewModel<STATE, SIDE_EFFECT>(
    private val networkMonitor: NetworkMonitor,
    dataStore: VoxyDataStoreImpl
) : ViewModel() {

    init {
        startNetworkMonitoring()
    }

    val isLoggedIn: StateFlow<Boolean> = dataStore
        .readIsUserLoggedInState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    val userData: StateFlow<OTPVerifyResponseDTO?> = dataStore
        .getObject<OTPVerifyResponseDTO>(LOGGED_IN_USER_DTO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    abstract fun setDefaultState(): STATE

    private val _state: MutableStateFlow<STATE> = MutableStateFlow(setDefaultState())
    val state: StateFlow<STATE> = _state

    private val _sideEffect: MutableSharedFlow<SIDE_EFFECT> =
        MutableSharedFlow(extraBufferCapacity = 1)
    val sideEffect: SharedFlow<SIDE_EFFECT> = _sideEffect
    protected fun updateState(oldState: (STATE) -> STATE) = viewModelScope.launch {
        _state.emit(oldState(_state.value))
    }

    fun postSideEffect(sideEffect: SIDE_EFFECT) = viewModelScope.launch {
        _sideEffect.emit(sideEffect)
    }

    protected open fun networkState(): StateFlow<NetworkInfo> = networkMonitor.networkState

    // (optional) convenience helpers
    protected val isConnected: Boolean
        get() = networkMonitor.networkState.value.state == NetworkState.CONNECTED

    protected fun startNetworkMonitoring() = networkMonitor.startMonitoring()
    protected fun stopNetworkMonitoring() = networkMonitor.stopMonitoring()

    override fun onCleared() {
        stopNetworkMonitoring()
        super.onCleared()
    }
}