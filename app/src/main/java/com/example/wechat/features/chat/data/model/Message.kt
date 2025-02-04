package com.example.wechat.features.chat.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val senderId: String = "",
    val senderName: String = "",
    val text:String = "",
    val createdAt:String = "",
    val photoUrl:String = ""
)
