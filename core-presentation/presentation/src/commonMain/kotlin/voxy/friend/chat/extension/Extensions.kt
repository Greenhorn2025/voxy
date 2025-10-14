package voxy.friend.chat.extension

fun Map<*, *>.getMapValue(key: String): Map<*, *>? {
    return this[key] as? Map<*, *>
}

fun Map<*, *>.getStringValue(key: String): String? {
    return this[key] as? String
}