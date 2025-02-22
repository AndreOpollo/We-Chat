package com.example.wechat.features.auth.di

import com.example.wechat.features.auth.data.models.repository.AuthRepositoryImpl
import com.example.wechat.features.auth.domain.repository.AuthRepository
import com.example.wechat.features.auth.presentation.viewmodel.login.LoginViewModel

import com.example.wechat.features.auth.presentation.viewmodel.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single<AuthRepository> {AuthRepositoryImpl(get(),get())  }
    viewModel {LoginViewModel(get())}
    viewModel {RegisterViewModel(get())}

}