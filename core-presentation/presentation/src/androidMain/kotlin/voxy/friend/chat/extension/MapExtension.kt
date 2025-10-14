package voxy.friend.chat.extension

import org.json.JSONArray
import org.json.JSONObject

fun JSONObject.toMap(): Map<String, Any?> {
    val result = mutableMapOf<String, Any?>()
    val it = keys()
    while (it.hasNext()) {
        val key = it.next()
        result[key] = unwrap(get(key))
    }
    return result
}

fun JSONArray.toList(): List<Any?> =
    (0 until length()).map { idx -> unwrap(opt(idx)) }

private fun unwrap(value: Any?): Any? = when (value) {
    is JSONObject -> value.toMap()
    is JSONArray -> value.toList()
    JSONObject.NULL -> null
    is Boolean, is Int, is Long, is Double, is String -> value
    null -> null
    else -> value.toString() // fallback (e.g., BigDecimal)
}