package voxy.friend.chat.common.di

import androidx.fragment.app.FragmentActivity
import java.lang.ref.WeakReference

actual class ActivityContextProvider actual constructor() {
    private var activityRef: WeakReference<FragmentActivity>? = null

    actual fun setActivity(activity: Any) {
        require(activity is FragmentActivity) {
            "Expected Android Activity but got ${activity::class.simpleName}"
        }
        activityRef = WeakReference(activity)
    }

    actual fun getActivity(): Any? {
        return activityRef?.get()
    }

    actual fun getContext(): Any? {
        return getActivity()
    }

    actual fun clear() {
        activityRef?.clear()
        activityRef = null
    }

    actual fun hasValidContext(): Boolean {
        return getActivity() != null
    }

    actual companion object {
        actual val instance: ActivityContextProvider by lazy {
            ActivityContextProvider()
        }
    }
}