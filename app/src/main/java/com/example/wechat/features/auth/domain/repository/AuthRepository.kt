package com.example.wechat.features.auth.domain.repository

import com.example.wechat.features.auth.data.models.User
import com.example.wechat.util.Result
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    suspend fun createUser(email:String,
                   username:String,
                   password:String,
                   photoUrl:String,
                   ):Flow<Result<AuthResult>>
    suspend fun loginUser(email: String,password: String):Flow<Result<AuthResult>>
    suspend fun logout()
    suspend fun currentUserId():String
    suspend fun isLoggedIn(): Boolean
}