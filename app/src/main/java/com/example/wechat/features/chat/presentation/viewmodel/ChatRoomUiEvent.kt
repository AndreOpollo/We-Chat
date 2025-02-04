package com.example.wechat.features.chat.presentation.viewmodel

sealed class ChatRoomUiEvent{
    data class SendChat(val message:String,val receiverId:String):ChatRoomUiEvent()
}