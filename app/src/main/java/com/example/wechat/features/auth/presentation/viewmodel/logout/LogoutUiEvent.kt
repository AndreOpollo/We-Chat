package com.example.wechat.features.auth.presentation.viewmodel.logout

sealed class LogoutUiEvent {
    data object Logout:LogoutUiEvent()
}