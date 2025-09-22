package voxy.friend.chat.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import voxy.friend.chat.viewmodel.PhoneHintViewModel

fun getLoginUiModule() = module {
    viewModel { PhoneHintViewModel(getPhoneHintUseCase = get()) }
}