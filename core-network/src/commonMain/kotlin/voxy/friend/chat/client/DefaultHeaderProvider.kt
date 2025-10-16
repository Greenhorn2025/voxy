package voxy.friend.chat.client

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import voxy.friend.chat.constants.DeviceIdentifier
import voxy.friend.chat.network.getPlatformName
import voxy.friend.chat.storage.TokenStorage

class DefaultHeaderProvider(
    private val tokenStorage: TokenStorage,
    coroutineScope: CoroutineScope,
    private val isCanary : Boolean = false
) : HeaderProvider {

    private var cachedToken: String? = null
    private var cachedHeaders: Map<String, String> = emptyMap()

    init {
        // Load token and update cached headers
        coroutineScope.launch {
            tokenStorage.getToken()?.let { token ->
                cachedToken = token
                updateCachedHeaders()
            }
        }
    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_DEVICE_ID = "deviceId"
        private const val HEADER_CONTENT_TYPE = "Content-Type"
        private const val HEADER_APP_VERSION = "App-Version"
        private const val HEADER_PLATFORM = "Platform"

        private const val CONTENT_TYPE_JSON = "application/json"
    }

    override suspend fun getAuthToken(): String? {
        return tokenStorage.getToken()
    }

    override suspend fun setAuthToken(token: String) {
        cachedToken = token
        tokenStorage.saveToken(token)
    }

    override suspend fun clearAuthToken() {
        cachedToken = null
        tokenStorage.clearToken()
    }

    override fun getDeviceId(): String {
        return DeviceIdentifier.getDeviceId()
    }

    override fun getHeaders(): Map<String, String> {
        return cachedHeaders.ifEmpty {
            buildHeaders()
        }
    }

    private fun buildHeaders(): Map<String, String> {
        val headers = mutableMapOf<String, String>()

        cachedToken?.let { token ->
            headers["Authorization"] = "Bearer $token"
        }

        headers["deviceId"] = getDeviceId()
        headers["Content-Type"] = getContentType()
        headers["App-Version"] = getAppVersion()
        headers["Platform"] = getPlatformName()
        headers["iscanary"] = isCanary.toString()

        return headers
    }

    private fun updateCachedHeaders() {
        cachedHeaders = buildHeaders()
    }

    override fun getContentType(): String {
        return CONTENT_TYPE_JSON
    }

    private fun getAppVersion(): String {
        // You can make this configurable
        return "1.0.0"
    }

    private fun getPlatform(): String {
        return getPlatformName()
    }
}
