package voxy.friend.chat.ai.network

enum class NetworkState {
    CONNECTED, SLOW_CONNECTION, CONNECTING, DISCONNECTED, CHECKING
}

enum class NetworkSpeed { FAST, MODERATE, SLOW, UNKNOWN }

enum class ConnectionType { WIFI, CELLULAR_5G, CELLULAR_4G, CELLULAR_3G, CELLULAR_2G, ETHERNET, UNKNOWN, NONE }
