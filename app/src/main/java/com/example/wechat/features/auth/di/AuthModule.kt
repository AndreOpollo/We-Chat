package com.example.wechat.features.auth.di

import com.example.wechat.features.auth.data.models.repository.AuthRepositoryImpl
import com.example.wechat.features.auth.domain.repository.AuthRepository
import com.example.wechat.features.auth.presentation.viewmodel.login.LoginViewModel
import com.example.wechat.features.auth.presentation.viewmodel.logout.LogoutViewModel
import com.example.wechat.features.auth.presentation.viewmodel.register.RegisterViewModel
import org.koin.dsl.module

val authModule = module {
    single<AuthRepository> {AuthRepositoryImpl(get(),get())  }
    single {LoginViewModel(get())}
    single {RegisterViewModel(get())}
    single {LogoutViewModel(get())}

}