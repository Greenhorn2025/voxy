package vocy.friend.chat.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import vocy.friend.chat.network.AndroidNetworkMonitor
import voxy.friend.chat.network.NetworkMonitor

fun androidNetworkModule() = module {
    single<CoroutineScope> { CoroutineScope(SupervisorJob() + Dispatchers.IO) }
    single<NetworkMonitor> { AndroidNetworkMonitor(androidContext(), get()) }
}