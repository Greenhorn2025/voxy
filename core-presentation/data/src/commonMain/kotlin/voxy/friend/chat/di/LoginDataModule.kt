package voxy.friend.chat.di

import org.koin.dsl.module
import voxy.friend.chat.repository.PhoneHintRepository
import voxy.friend.chat.repository.PhoneHintRepositoryImpl

fun getLoginDataModule() = module {
    single<PhoneHintRepository> { PhoneHintRepositoryImpl(phoneHintDataSource = get()) }
}