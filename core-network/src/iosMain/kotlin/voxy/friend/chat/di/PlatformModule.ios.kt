package voxy.friend.chat.di

import voxy.friend.chat.constants.DeviceIdentifier
import voxy.friend.chat.constants.IosDeviceIdentifier

actual fun getPlatformDeviceIdentifier(context: Any): DeviceIdentifier {
    return IosDeviceIdentifier()
}