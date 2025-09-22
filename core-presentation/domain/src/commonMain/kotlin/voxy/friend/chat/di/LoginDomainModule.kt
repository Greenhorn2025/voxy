package voxy.friend.chat.di

import org.koin.dsl.module
import voxy.friend.chat.usecase.GetPhoneHintUseCase

fun getLoginDomainModule() = module {
    single { GetPhoneHintUseCase(phoneHintRepository = get()) }
}