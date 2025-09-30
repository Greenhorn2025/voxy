package voxy.friend.chat.ai.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import voxy.friend.chat.ai.network.AndroidNetworkMonitor
import voxy.friend.chat.ai.network.NetworkMonitor

val androidNetworkModule = module {
    single<NetworkMonitor> { AndroidNetworkMonitor(androidContext()) }
}