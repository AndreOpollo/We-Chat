package com.example.wechat.features.chat.di

import com.example.wechat.features.chat.data.repository.ChatRoomRepositoryImpl
import com.example.wechat.features.chat.domain.repository.ChatRoomRepository
import com.example.wechat.features.chat.presentation.viewmodel.ChatRoomViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val chatModule = module {
    single<ChatRoomRepository> { ChatRoomRepositoryImpl(get(),get()) }
    viewModel { (receiverId:String)->
        ChatRoomViewModel(get(),get(),receiverId) }
}