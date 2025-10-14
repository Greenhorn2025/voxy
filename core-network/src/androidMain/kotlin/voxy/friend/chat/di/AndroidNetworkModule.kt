package voxy.friend.chat.di

import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import voxy.friend.chat.network.AndroidNetworkMonitor
import voxy.friend.chat.network.NetworkMonitor
import voxy.friend.chat.networkinspector.setupAndroidClient
import voxy.friend.chat.storage.SecureStorage

fun androidNetworkModule() = module {
    single<SecureStorage> { SecureStorage(context = androidContext()) }
    single<HttpClient> { setupAndroidClient(androidContext()) }
    single<CoroutineScope> { CoroutineScope(context = SupervisorJob() + Dispatchers.IO) }
    single<NetworkMonitor> {
        AndroidNetworkMonitor(
            context = androidContext(),
            coroutineScope = get()
        )
    }
}