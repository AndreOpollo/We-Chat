package com.example.wechat.features.auth.presentation.viewmodel.login

sealed class LoginUiEvent(){
    data class LoginUser(val email:String,val password:String):LoginUiEvent()
}