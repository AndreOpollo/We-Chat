package com.example.wechat.features.home.di

import com.example.wechat.features.home.data.repository.HomeRepositoryImpl
import com.example.wechat.features.home.domain.repository.HomeRepository
import com.example.wechat.features.home.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val homeModule = module {
    single<HomeRepository>{HomeRepositoryImpl(get(),get()) }
    viewModel{HomeViewModel(get(),get())}
}