package voxy.friend.chat.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import voxy.friend.chat.datastore.VoxyDataStoreImpl
import voxy.friend.chat.datastore.dataStoreFileName
import voxy.friend.chat.datastore.initDataStore

actual fun platformModule() = module {
    single<Factory> {
        Factory(androidContext() as Application)
    }

    single<VoxyDataStoreImpl> {
        val factory = get<Factory>()
        factory.createDataStore()
    }
}

actual class Factory(private val context: Application){
    val dataStore = initDataStore {
        context.filesDir.resolve(dataStoreFileName).absolutePath
    }
    actual fun createDataStore(): VoxyDataStoreImpl = VoxyDataStoreImpl(dataStore)
}