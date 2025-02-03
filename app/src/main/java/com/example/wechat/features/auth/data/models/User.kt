package com.example.wechat.features.auth.data.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id:String = "",
    val username:String = "",
    val photoUrl:String = "",
    val email:String = "",
    val createdAt:String = ""
)
