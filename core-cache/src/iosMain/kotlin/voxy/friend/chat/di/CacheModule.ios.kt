package voxy.friend.chat.di

import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask
import voxy.friend.chat.datastore.VoxyDataStoreImpl
import voxy.friend.chat.datastore.dataStoreFileName
import voxy.friend.chat.datastore.initDataStore


actual fun platformModule() = module {
    single<VoxyDataStoreImpl> { Factory().createDataStore() }
}

actual class Factory {
    @OptIn(ExperimentalForeignApi::class)
    actual fun createDataStore(): VoxyDataStoreImpl {
        val dataStore = initDataStore {
            val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null,
            )
            requireNotNull(documentDirectory).path + "/$dataStoreFileName"
        }

        return VoxyDataStoreImpl(dataStore)
    }
}