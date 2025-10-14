package voxy.friend.chat.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import voxy.friend.chat.viewmodel.ChatViewModel
import voxy.friend.chat.viewmodel.OTPLessViewModel
import voxy.friend.chat.viewmodel.OnBoardingViewModel
import voxy.friend.chat.viewmodel.PhoneHintViewModel
import voxy.friend.chat.viewmodel.SharedViewModel

fun getViewModelModule() = module {
    viewModel {
        PhoneHintViewModel(
            getPhoneHintUseCase = get(),
            monitor = get(),
            dataStore = get()
        )
    }
    viewModel {
        OnBoardingViewModel(
            networkMonitor = get(),
            dataStore = get(),
            onBoardingUseCase = get(),
            truecallerRepository = get(),
            headerProvider = get()
        )
    }
    viewModel { OTPLessViewModel(networkMonitor = get(), dataStore = get(), otplessManager = get(), onBoardingUseCase = get()) }
    viewModel { SharedViewModel(monitor = get(), dataStore = get()) }
    viewModel { ChatViewModel(networkMonitor = get(), dataStore = get()) }
}

expect val platformAuthModule: Module
