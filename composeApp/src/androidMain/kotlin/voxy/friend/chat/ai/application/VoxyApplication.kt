package voxy.friend.chat.ai.application

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.android.inject
import org.koin.dsl.module
import org.koin.mp.KoinPlatform.getKoin
import voxy.friend.chat.ai.di.getPhoneHintDataSource
import voxy.friend.chat.ai.di.initKoin
import voxy.friend.chat.common.di.androidModule
import voxy.friend.chat.di.androidNetworkModule
import voxy.friend.chat.di.networkModule
import voxy.friend.chat.manager.AppLifecycleManager
import voxy.friend.chat.networkinspector.KoinHelper
import voxy.friend.chat.repository.ChatRepository

class VoxyApplication : Application() {

    private val chatRepository: ChatRepository by inject()


    override fun onCreate() {
        super.onCreate()
        FirebaseCrashlytics.getInstance().isCrashlyticsCollectionEnabled = false
        multiplatform.network.cmptoast.AppContext.apply { set(applicationContext) }
        Napier.base(DebugAntilog())
        initKoin {
            it.modules(androidNetworkModule())
            it.modules(networkModule)
            it.modules(androidModule)
            it.modules(getPhoneHintDataSource())
            it.modules(
                module {
                    single { this@VoxyApplication.applicationContext }
                },
            )
        }
        KoinHelper.koin = getKoin()

        val lifecycleManager = AppLifecycleManager(this, chatRepository)
        ProcessLifecycleOwner.get().lifecycle.addObserver(lifecycleManager)

        // Initialize WorkManager
//        WorkManager.initialize(this, workManagerConfiguration)

    }

//    override val workManagerConfiguration: Configuration
//        get() = Configuration.Builder()
//            .setMinimumLoggingLevel(android.util.Log.INFO)
//            .build()
}