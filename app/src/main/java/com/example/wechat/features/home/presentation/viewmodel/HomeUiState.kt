package com.example.wechat.features.home.presentation.viewmodel

import com.example.wechat.features.auth.data.models.User

data class HomeUiState(
    val isLoading:Boolean = false,
    val success:String?=null,
    val error:String?=null,
    val users:List<User> = emptyList()
)