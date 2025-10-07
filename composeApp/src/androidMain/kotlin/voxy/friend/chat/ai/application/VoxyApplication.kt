package voxy.friend.chat.ai.application

import android.app.Application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.dsl.module
import vocy.friend.chat.di.androidNetworkModule
import voxy.friend.chat.ai.di.getPhoneHintDataSource
import voxy.friend.chat.ai.di.initKoin

class VoxyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        multiplatform.network.cmptoast.AppContext.apply { set(applicationContext) }
        Napier.base(DebugAntilog())
        initKoin {
            it.modules(androidNetworkModule())
            it.modules(getPhoneHintDataSource())
            it.modules(
                module {
                    single { this@VoxyApplication.applicationContext }
                },
            )
        }
    }
}