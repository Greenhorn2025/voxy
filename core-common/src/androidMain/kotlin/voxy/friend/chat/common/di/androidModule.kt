package voxy.friend.chat.common.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import voxy.friend.chat.common.database.VoxyDatabase
import voxy.friend.chat.common.database.dao.ConversationDao
import voxy.friend.chat.common.database.dao.MessageDao
import voxy.friend.chat.common.database.dao.UserProfileDao
import voxy.friend.chat.common.database.getDatabaseBuilder

val androidModule = module {
    single<VoxyDatabase> {
        getDatabaseBuilder(androidContext()).build()
    }
    single<MessageDao> { get<VoxyDatabase>().messageDao() }
    single<ConversationDao> { get<VoxyDatabase>().conversationDao() }
    single<UserProfileDao> { get<VoxyDatabase>().userProfileDao() }
}