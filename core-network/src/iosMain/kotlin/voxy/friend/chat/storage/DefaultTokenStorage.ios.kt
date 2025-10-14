package voxy.friend.chat.storage

import kotlinx.cinterop.memScoped
import platform.CoreFoundation.CFDictionaryRef
import platform.CoreFoundation.CFTypeRefVar
import platform.CoreFoundation.kCFBooleanTrue
import platform.Foundation.NSData
import platform.Security.SecItemAdd
import platform.Security.SecItemCopyMatching
import platform.Security.SecItemDelete
import platform.Security.errSecSuccess
import platform.Security.kSecAttrAccount
import platform.Security.kSecClass
import platform.Security.kSecClassGenericPassword
import platform.Security.kSecMatchLimit
import platform.Security.kSecReturnData
import platform.Security.kSecValueData

actual class SecureStorage {
    actual fun save(key: String, value: String) {
        val query = mutableMapOf<Any?, Any?>()
        query[kSecClass] = kSecClassGenericPassword
        query[kSecAttrAccount] = key
        query[kSecValueData] = value.encodeToByteArray().toNSData()

        SecItemDelete(query as CFDictionaryRef)
        SecItemAdd(query as CFDictionaryRef, null)
    }

    actual fun get(key: String): String? {
        val query = mutableMapOf<Any?, Any?>()
        query[kSecClass] = kSecClassGenericPassword
        query[kSecAttrAccount] = key
        query[kSecReturnData] = kCFBooleanTrue
        query[kSecMatchLimit] = kSecMatchLimitOne

        val result = memScoped {
            val resultRef = alloc<CFTypeRefVar>()
            val status = SecItemCopyMatching(query as CFDictionaryRef, resultRef.ptr)

            if (status == errSecSuccess) {
                (resultRef.value as? NSData)?.toByteArray()?.decodeToString()
            } else {
                null
            }
        }

        return result
    }

    actual fun remove(key: String) {
        val query = mutableMapOf<Any?, Any?>()
        query[kSecClass] = kSecClassGenericPassword
        query[kSecAttrAccount] = key
        SecItemDelete(query as CFDictionaryRef)
    }

    actual fun clear() {
        val query = mutableMapOf<Any?, Any?>()
        query[kSecClass] = kSecClassGenericPassword
        SecItemDelete(query as CFDictionaryRef)
    }
}

private fun ByteArray.toNSData(): NSData {
    return NSData.create(bytes = this.refTo(0), length = this.size.toULong())
}

private fun NSData.toByteArray(): ByteArray {
    return ByteArray(this.length.toInt()).apply {
        usePinned {
            memcpy(it.addressOf(0), this@toByteArray.bytes, this@toByteArray.length)
        }
    }
}