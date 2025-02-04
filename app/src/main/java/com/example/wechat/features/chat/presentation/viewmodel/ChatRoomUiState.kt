package com.example.wechat.features.chat.presentation.viewmodel

import com.example.wechat.features.chat.data.model.Message

data class ChatRoomUiState(
    val isLoading:Boolean = false,
    val messages:List<Message> = emptyList(),
    val success: String? = null,
    val error: String? = null
)