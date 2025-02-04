package com.example.wechat.features.chat.domain.repository

import com.example.wechat.features.chat.data.model.Message
import com.example.wechat.util.Result
import kotlinx.coroutines.flow.Flow

interface ChatRoomRepository {
    suspend fun sendChat(message: String,receiverId:String): Flow<Result<Unit>>
    suspend fun fetchChats(senderId:String,receiverId: String): Flow<Result<List<Message>>>
}