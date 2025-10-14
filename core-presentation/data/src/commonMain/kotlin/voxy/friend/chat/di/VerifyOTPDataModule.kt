package voxy.friend.chat.di

import org.koin.dsl.module
import voxy.friend.chat.repository.VerifyOTPRepository
import voxy.friend.chat.repository.VerifyOTPRepositoryImpl

fun verifyOTPDataModule() = module {
    single<VerifyOTPRepository> { VerifyOTPRepositoryImpl(get()) }
}