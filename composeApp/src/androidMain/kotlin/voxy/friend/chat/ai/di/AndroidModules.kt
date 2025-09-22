package voxy.friend.chat.ai.di

import org.koin.dsl.module
import voxy.friend.chat.ai.phonehint.AndroidPhoneHintDataSource
import voxy.friend.chat.source.PhoneHintDataSource

fun getPhoneHintDataSource() = module {
    single<PhoneHintDataSource> { params ->
        AndroidPhoneHintDataSource()
    }
}