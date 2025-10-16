package voxy.friend.chat.common.di

import org.koin.dsl.module
import voxy.friend.chat.common.database.VoxyDatabase

val appModule = module {
    single { get<VoxyDatabase>().messageDao() }
    single { get<VoxyDatabase>().conversationDao() }
    single { get<VoxyDatabase>().userProfileDao() }
}