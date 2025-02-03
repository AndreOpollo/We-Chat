package com.example.wechat.features.auth.presentation.viewmodel.login

import com.example.wechat.features.auth.data.models.User

data class LoginUiState(
    val isLoading: Boolean = false,
    val success: String? =null,
    val error: String? =null,
    val user: User?=null
)
