package voxy.friend.chat.common.di

import org.koin.dsl.module
import voxy.friend.chat.common.database.DatabaseBuilder
import voxy.friend.chat.common.database.VoxyDatabase
import voxy.friend.chat.common.database.dao.ConversationDao
import voxy.friend.chat.common.database.dao.MessageDao
import voxy.friend.chat.common.database.dao.UserProfileDao

val iosModule = module {
    single<VoxyDatabase> {
        DatabaseBuilder().build()
    }

    single<MessageDao> { get<VoxyDatabase>().messageDao() }
    single<ConversationDao> { get<VoxyDatabase>().conversationDao() }
    single<UserProfileDao> { get<VoxyDatabase>().userProfileDao() }
}