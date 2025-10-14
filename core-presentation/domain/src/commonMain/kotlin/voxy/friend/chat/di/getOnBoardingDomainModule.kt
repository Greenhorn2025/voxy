package voxy.friend.chat.di

import org.koin.dsl.module
import voxy.friend.chat.usecase.OnBoardingUseCase

fun getOnBoardingDomainModule() = module {
    single { OnBoardingUseCase(signUpRepository = get(), otpRepository = get()) }
}