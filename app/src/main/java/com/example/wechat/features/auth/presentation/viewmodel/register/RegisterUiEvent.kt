package com.example.wechat.features.auth.presentation.viewmodel.register

sealed class RegisterUiEvent{
    data class RegisterUser(val username:String,
                            val email:String,
                            val password:String,
        val photoUrl:String):RegisterUiEvent()
}