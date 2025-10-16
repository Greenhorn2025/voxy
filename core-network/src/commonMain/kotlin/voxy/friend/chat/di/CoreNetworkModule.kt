package voxy.friend.chat.di

import org.koin.dsl.module
import voxy.friend.chat.apiService.OTPVerifyService
import voxy.friend.chat.apiService.OnBoardingService
import voxy.friend.chat.build.PlatformDebug
import voxy.friend.chat.client.DefaultHeaderProvider
import voxy.friend.chat.client.HeaderProvider
import voxy.friend.chat.client.KtorClient
import voxy.friend.chat.networkinspector.NetworkInspector
import voxy.friend.chat.networkinspector.createNetworkInspector
import voxy.friend.chat.storage.DefaultTokenStorage
import voxy.friend.chat.storage.TokenStorage

fun getCoreNetworkModule() = module {
    single { OnBoardingService(client = get()) }
    single { OTPVerifyService(client = get()) }
}

val networkModule = module {
    single<TokenStorage> { DefaultTokenStorage(get()) }

    single<HeaderProvider> {
        DefaultHeaderProvider(
            tokenStorage = get(),
            coroutineScope = get(),
            isCanary = PlatformDebug.isDebug
        )
    }

    // Network Inspector
    single<NetworkInspector> { createNetworkInspector() }

    // HTTP Client
    single {
        KtorClient(
            inspector = get(),
            enableLogging = PlatformDebug.isDebug,
            headerProvider = get()
        ).getInstance()
    }
}