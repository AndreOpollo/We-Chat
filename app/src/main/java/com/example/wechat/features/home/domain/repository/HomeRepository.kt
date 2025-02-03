package com.example.wechat.features.home.domain.repository

import com.example.wechat.features.auth.data.models.User
import com.example.wechat.util.Result
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getAllUsers():Flow<Result<List<User>>>
    suspend fun getAllUsersWithActiveChats():Flow<Result<List<User>>>
}