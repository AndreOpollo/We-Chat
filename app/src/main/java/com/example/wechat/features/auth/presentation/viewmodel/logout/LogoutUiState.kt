package com.example.wechat.features.auth.presentation.viewmodel.logout

data class LogoutUiState(
    val isLoading:Boolean = false,
    val success:String?=null,
    val error:String?=null
)