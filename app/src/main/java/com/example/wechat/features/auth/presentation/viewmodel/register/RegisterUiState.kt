package com.example.wechat.features.auth.presentation.viewmodel.register

import com.example.wechat.features.auth.data.models.User

data class RegisterUiState(
    val isLoading: Boolean = false,
    val error:String?=null,
    val success:String?=null,
)
