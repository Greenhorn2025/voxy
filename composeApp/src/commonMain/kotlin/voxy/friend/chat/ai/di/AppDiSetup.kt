package voxy.friend.chat.ai.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import voxy.friend.chat.di.getLoginDataModule
import voxy.friend.chat.di.getLoginDomainModule
import voxy.friend.chat.di.getLoginUiModule

fun initKoin(koinApplication: ((KoinApplication) -> Unit)? = null ) {
   startKoin {
       koinApplication?.invoke(this)
        modules(
            getLoginDataModule(),
            androidNetworkModule(),
            getLoginDomainModule(),
            getLoginUiModule(),
        )
    }
}
