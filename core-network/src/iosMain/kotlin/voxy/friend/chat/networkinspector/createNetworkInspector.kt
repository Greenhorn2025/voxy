package voxy.friend.chat.networkinspector

actual fun createNetworkInspector(): NetworkInspector = IOSNetworkInspector()