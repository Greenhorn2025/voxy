package voxy.friend.chat.ai.application

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.dsl.module
import org.koin.mp.KoinPlatform.getKoin
import voxy.friend.chat.ai.di.getPhoneHintDataSource
import voxy.friend.chat.ai.di.initKoin
import voxy.friend.chat.di.androidNetworkModule
import voxy.friend.chat.di.networkModule
import voxy.friend.chat.networkinspector.KoinHelper

class VoxyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseCrashlytics.getInstance().isCrashlyticsCollectionEnabled = false
        multiplatform.network.cmptoast.AppContext.apply { set(applicationContext) }
        Napier.base(DebugAntilog())
        initKoin {
            it.modules(androidNetworkModule())
            it.modules(networkModule)
            it.modules(getPhoneHintDataSource())
            it.modules(
                module {
                    single { this@VoxyApplication.applicationContext }
                },
            )
        }
        KoinHelper.koin = getKoin()
    }
}