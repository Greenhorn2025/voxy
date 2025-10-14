package voxy.friend.chat.di

import org.koin.dsl.module
import voxy.friend.chat.auth.AndroidTrueCallerRepository
import voxy.friend.chat.auth.TrueCallerFactory
import voxy.friend.chat.auth.TrueCallerRepository
import voxy.friend.chat.common.di.ActivityContextProvider
import voxy.friend.chat.otpless.OTPLessManager

actual val platformAuthModule = module {
    single { ActivityContextProvider.instance }
    single { TrueCallerFactory(get()).create() }
    single<TrueCallerRepository> { AndroidTrueCallerRepository(get()) }
    single {
        OTPLessManager(get())
    }
}