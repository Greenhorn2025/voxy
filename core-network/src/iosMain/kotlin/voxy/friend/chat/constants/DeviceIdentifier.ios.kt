package voxy.friend.chat.constants

import platform.Foundation.NSUUID
import platform.UIKit.UIDevice

actual object DeviceIdentifier {
    actual fun getDeviceId(): String {
        // Use identifierForVendor – resets on reinstall
        return UIDevice.currentDevice.identifierForVendor?.UUIDString
            ?: NSUUID().UUIDString()
    }
}