package com.example.wechat.features.auth.domain.repository

import com.example.wechat.features.auth.data.models.User
import com.example.wechat.util.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun createUser(email:String,
                   username:String,
                   password:String,
                   photoUrl:String,
                   ):Result<User>
    suspend fun loginUser(email: String,password: String):Result<User>
}