package com.example.wechat.features.auth.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id")
    val id:String = "",
    @SerialName("username")
    val username:String = "",
    @SerialName("photoUrl")
    val photoUrl:String = "",
    @SerialName("email")
    val email:String = "",
    @SerialName("createdAt")
    val createdAt:String = "",
    @SerialName("lastMessage")
    var lastMessage: String? = null,
    @SerialName("lastMessageTimeStamp")
    var lastMessageTimeStamp: String? = null

)
