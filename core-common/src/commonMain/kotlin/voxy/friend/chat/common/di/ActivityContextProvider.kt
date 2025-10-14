package voxy.friend.chat.common.di

expect class ActivityContextProvider() {
    /**
     * Store the current activity/view controller context
     * Android: Call this in Activity's onCreate() or onResume()
     * iOS: Call this in ViewController's viewDidLoad() or viewWillAppear()
     */
    fun setActivity(activity: Any)

    /**
     * Get the current activity/view controller context
     * Android: Returns Activity?
     * iOS: Returns UIViewController?
     */
    fun getActivity(): Any?

    /**
     * Get the current context (platform-agnostic)
     * Android: Returns Context?
     * iOS: Returns UIViewController?
     */
    fun getContext(): Any?

    /**
     * Clear the stored reference
     * Android: Call this in Activity's onDestroy()
     * iOS: Call this in ViewController's viewDidDisappear() or deinit
     */
    fun clear()

    /**
     * Check if a valid context is available
     */
    fun hasValidContext(): Boolean

    companion object {
        /**
         * Singleton instance
         */
        val instance: ActivityContextProvider
    }
}