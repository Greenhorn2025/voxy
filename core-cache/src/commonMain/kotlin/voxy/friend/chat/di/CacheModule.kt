package voxy.friend.chat.di

import org.koin.core.module.Module
import org.koin.dsl.module
import voxy.friend.chat.datastore.VoxyDataStoreImpl

expect fun platformModule(): Module

expect class Factory {
    fun createDataStore(): VoxyDataStoreImpl
}

val cacheModule = module {
    includes(platformModule())
}