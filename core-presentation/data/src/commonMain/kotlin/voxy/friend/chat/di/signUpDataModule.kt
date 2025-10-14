package voxy.friend.chat.di

import org.koin.dsl.module
import voxy.friend.chat.repository.SignUpRepository
import voxy.friend.chat.repository.SignUpRepositoryImpl

fun signUpDataModule() = module{
    single<SignUpRepository> { SignUpRepositoryImpl(onBoardingService = get()) }
}