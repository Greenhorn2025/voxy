package vocy.friend.chat.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import voxy.friend.chat.network.ConnectionType
import voxy.friend.chat.network.NetworkInfo
import voxy.friend.chat.network.NetworkMonitor
import voxy.friend.chat.network.NetworkSpeed
import voxy.friend.chat.network.NetworkState
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class AndroidNetworkMonitor(
    context: Context,
    private val coroutineScope: CoroutineScope,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NetworkMonitor {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val _networkState = MutableStateFlow(NetworkInfo())
    private var networkCallback: ConnectivityManager.NetworkCallback? = null

    override val networkState: StateFlow<NetworkInfo>
        get() = _networkState

    override fun startMonitoring() {
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                coroutineScope.launch(ioDispatcher) {
                    updateNetworkInfo(connectivityManager.getNetworkCapabilities(network))
                }
            }

            override fun onLost(network: Network) {
                _networkState.value = NetworkInfo(
                    state = NetworkState.DISCONNECTED,
                    type = ConnectionType.NONE
                )
            }

            override fun onCapabilitiesChanged(network: Network, caps: NetworkCapabilities) {
                coroutineScope.launch(ioDispatcher) { updateNetworkInfo(caps) }
            }
        }
        connectivityManager.registerNetworkCallback(request, networkCallback!!)
        coroutineScope.launch(ioDispatcher) { checkCurrentConnection() }
    }

    override fun stopMonitoring() {
        networkCallback?.let { connectivityManager.unregisterNetworkCallback(it) }
    }

    private suspend fun checkCurrentConnection() = withContext(ioDispatcher) {
        val active = connectivityManager.activeNetwork
        val caps = active?.let { connectivityManager.getNetworkCapabilities(it) }
        updateNetworkInfo(caps)
    }

    private suspend fun updateNetworkInfo(caps: NetworkCapabilities?) {
        if (caps == null) {
            _networkState.value = NetworkInfo(NetworkState.DISCONNECTED, type = ConnectionType.NONE)
            return
        }

        val type = when {
            caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> ConnectionType.WIFI
            caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> ConnectionType.CELLULAR_4G
            caps.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> ConnectionType.ETHERNET
            else -> ConnectionType.UNKNOWN
        }

        val down = caps.linkDownstreamBandwidthKbps / 1000f
        val up = caps.linkUpstreamBandwidthKbps / 1000f
        val speed = when {
            down > 10 -> NetworkSpeed.FAST
            down > 1 -> NetworkSpeed.MODERATE
            down > 0 -> NetworkSpeed.SLOW
            else -> NetworkSpeed.UNKNOWN
        }

        _networkState.value = NetworkInfo(
            state = if (caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED))
                NetworkState.CONNECTED else NetworkState.DISCONNECTED,
            speed = speed,
            type = type,
            downloadSpeedMbps = down,
            uploadSpeedMbps = up,
            hasInternetAccess = caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED),
            pingMs = measurePing()
        )
    }

    private suspend fun measurePing(): Int = withContext(ioDispatcher) {
        try {
            val start = System.currentTimeMillis()
            (URL("https://www.google.com").openConnection() as HttpURLConnection).apply {
                connectTimeout = 1000; connect()
            }
            (System.currentTimeMillis() - start).toInt()
        } catch (e: IOException) {
            println(e.message)
            999
        }
    }
}