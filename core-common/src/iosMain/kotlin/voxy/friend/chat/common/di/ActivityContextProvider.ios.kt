package voxy.friend.chat.common.di

actual class ActivityContextProvider actual constructor() {
    actual fun setActivity(activity: Any) {
    }

    actual fun getActivity(): Any? {
        TODO("Not yet implemented")
    }

    actual fun getContext(): Any? {
        TODO("Not yet implemented")
    }

    actual fun clear() {
    }

    actual fun hasValidContext(): Boolean {
        TODO("Not yet implemented")
    }

    actual companion object {
        actual val instance: ActivityContextProvider
            get() = TODO("Not yet implemented")
    }
}