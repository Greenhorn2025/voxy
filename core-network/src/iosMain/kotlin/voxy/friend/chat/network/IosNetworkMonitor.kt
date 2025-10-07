package voxy.friend.chat.network

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import platform.Network.nw_interface_type_cellular
import platform.Network.nw_interface_type_wifi
import platform.Network.nw_interface_type_wired
import platform.Network.nw_path_get_status
import platform.Network.nw_path_monitor_cancel
import platform.Network.nw_path_monitor_create
import platform.Network.nw_path_monitor_set_queue
import platform.Network.nw_path_monitor_set_update_handler
import platform.Network.nw_path_monitor_start
import platform.Network.nw_path_monitor_t
import platform.Network.nw_path_status_satisfied
import platform.Network.nw_path_uses_interface_type
import platform.darwin.dispatch_get_main_queue

class IosNetworkMonitor : NetworkMonitor {
    private val _networkState = MutableStateFlow(NetworkInfo())
    override val networkState: StateFlow<NetworkInfo> = _networkState

    private var monitor: nw_path_monitor_t? = null

    override fun startMonitoring() {
        monitor = nw_path_monitor_create()
        nw_path_monitor_set_queue(monitor, dispatch_get_main_queue())
        nw_path_monitor_set_update_handler(monitor) { path ->
            val status = nw_path_get_status(path)
            val connected = status == nw_path_status_satisfied

            _networkState.value = NetworkInfo(
                state = if (connected) NetworkState.CONNECTED else NetworkState.DISCONNECTED,
                hasInternetAccess = connected,
                type = when {
                    nw_path_uses_interface_type(path, nw_interface_type_wifi) -> ConnectionType.WIFI
                    nw_path_uses_interface_type(path, nw_interface_type_cellular) -> ConnectionType.CELLULAR_4G
                    nw_path_uses_interface_type(path, nw_interface_type_wired) -> ConnectionType.ETHERNET
                    else -> ConnectionType.UNKNOWN
                }
            )
        }
        nw_path_monitor_start(monitor)
    }

    override fun stopMonitoring() {
        monitor?.let { nw_path_monitor_cancel(it) }
    }
}