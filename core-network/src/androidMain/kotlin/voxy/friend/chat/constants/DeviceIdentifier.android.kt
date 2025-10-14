package voxy.friend.chat.constants

import android.content.Context
import android.provider.Settings
import org.koin.mp.KoinPlatform.getKoin


actual object DeviceIdentifier {
    actual fun getDeviceId(): String {
        val context: Context = getKoin().get()
        return Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        ) ?: "unknown_device"
    }
}