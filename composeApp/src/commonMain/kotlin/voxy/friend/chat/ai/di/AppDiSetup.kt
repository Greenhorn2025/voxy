package voxy.friend.chat.ai.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import voxy.friend.chat.common.di.appModule
import voxy.friend.chat.di.cacheModule
import voxy.friend.chat.di.chatDataModule
import voxy.friend.chat.di.getChatDomainModule
import voxy.friend.chat.di.getCoreNetworkModule
import voxy.friend.chat.di.getLoginDataModule
import voxy.friend.chat.di.getLoginDomainModule
import voxy.friend.chat.di.getOnBoardingDomainModule
import voxy.friend.chat.di.getViewModelModule
import voxy.friend.chat.di.platformAuthModule
import voxy.friend.chat.di.signUpDataModule
import voxy.friend.chat.di.verifyOTPDataModule

fun initKoin(koinApplication: ((KoinApplication) -> Unit)? = null ) {
   startKoin {
       koinApplication?.invoke(this)
        modules(
            platformAuthModule,
            cacheModule,
            appModule,
            getCoreNetworkModule(),
            getLoginDataModule(),
            signUpDataModule(),
            chatDataModule(),
            verifyOTPDataModule(),
            getChatDomainModule(),
            getOnBoardingDomainModule(),
            getLoginDomainModule(),
            getViewModelModule(),
        )
    }
}
