package voxy.friend.chat.di

import org.koin.dsl.module
import voxy.friend.chat.usecase.ChatMessageUseCase

fun getChatDomainModule() = module {
    single { ChatMessageUseCase(chatRepository = get()) }
}