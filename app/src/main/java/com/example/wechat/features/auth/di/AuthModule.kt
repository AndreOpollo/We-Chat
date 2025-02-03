package com.example.wechat.features.auth.di

import com.example.wechat.features.auth.data.models.repository.AuthRepositoryImpl
import com.example.wechat.features.auth.domain.repository.AuthRepository
import org.koin.dsl.module

val authModule = module {
    single<AuthRepository> {AuthRepositoryImpl(get(),get())  }

}