package voxy.friend.chat.ai

import android.app.Activity
import android.content.Context
import java.lang.ref.WeakReference

/**
 * Simple helper class to store and provide Activity context for Koin dependency injection
 */
object ActivityContextProvider {
    
    private var activityRef: WeakReference<Activity>? = null
    
    /**
     * Store the current activity context
     * Call this in your activity's onCreate() or onResume()
     */
    fun setActivity(activity: Activity) {
        activityRef = WeakReference(activity)
    }
    
    /**
     * Get the current activity context
     * Returns null if no activity is set or if it has been garbage collected
     */
    fun getActivity(): Activity? {
        return activityRef?.get()
    }
    
    /**
     * Get the current activity context as Context
     * Returns null if no activity is set or if it has been garbage collected
     */
    fun getContext(): Context? {
        return getActivity()
    }
    
    /**
     * Clear the stored activity reference
     * Call this in your activity's onDestroy()
     */
    fun clear() {
        activityRef?.clear()
        activityRef = null
    }
    
    /**
     * Check if a valid activity context is available
     */
    fun hasValidContext(): Boolean {
        return getActivity() != null
    }
}