package voxy.friend.chat.network

data class NetworkInfo(
    val state: NetworkState = NetworkState.CHECKING,
    val speed: NetworkSpeed = NetworkSpeed.UNKNOWN,
    val type: ConnectionType = ConnectionType.UNKNOWN,
    val signalStrength: Int = 0,
    val hasInternetAccess: Boolean = false,
    val downloadSpeedMbps: Float = 0f,
    val uploadSpeedMbps: Float = 0f,
    val pingMs: Int = 0
)