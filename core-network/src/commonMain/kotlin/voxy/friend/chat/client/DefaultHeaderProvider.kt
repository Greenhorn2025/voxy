package voxy.friend.chat.client

import voxy.friend.chat.constants.DeviceIdentifier
import voxy.friend.chat.network.getPlatformName
import voxy.friend.chat.storage.TokenStorage

class DefaultHeaderProvider(
    private val tokenStorage: TokenStorage
) : HeaderProvider {

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_DEVICE_ID = "deviceId"
        private const val HEADER_CONTENT_TYPE = "Content-Type"
        private const val HEADER_APP_VERSION = "App-Version"
        private const val HEADER_PLATFORM = "Platform"

        private const val CONTENT_TYPE_JSON = "application/json"
    }

    override fun getAuthToken(): String? {
        return tokenStorage.getToken()
    }

    override fun setAuthToken(token: String) {
        tokenStorage.saveToken(token)
    }

    override fun clearAuthToken() {
        tokenStorage.clearToken()
    }

    override fun getDeviceId(): String {
        return DeviceIdentifier.getDeviceId()
    }

    override fun getHeaders(): Map<String, String> {
        val headers = mutableMapOf<String, String>()

        // Add auth token if available
        getAuthToken()?.let { token ->
            headers[HEADER_AUTHORIZATION] = "Bearer $token"
        }

        // Add device ID
        headers[HEADER_DEVICE_ID] = getDeviceId()

        // Add content type
        headers[HEADER_CONTENT_TYPE] = getContentType()

        // Add app version
        headers[HEADER_APP_VERSION] = getAppVersion()

        // Add platform
        headers[HEADER_PLATFORM] = getPlatform()

        return headers
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
