package voxy.friend.chat.common.extension

fun String.extractPhoneNumber(): String {
    val digitsOnly = this.filter { it.isDigit() }
    return if (digitsOnly.length > 10) {
        digitsOnly.takeLast(10)
    } else {
        digitsOnly
    }
}