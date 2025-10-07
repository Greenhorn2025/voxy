package voxy.friend.chat.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import voxy.friend.chat.viewmodel.ChatViewModel
import voxy.friend.chat.viewmodel.PhoneHintViewModel
import voxy.friend.chat.viewmodel.SharedViewModel

fun getLoginUiModule() = module {
    viewModel { PhoneHintViewModel(getPhoneHintUseCase = get(), monitor = get()) }
    viewModel { SharedViewModel(monitor = get()) }
    viewModel { ChatViewModel(networkMonitor = get()) }
}