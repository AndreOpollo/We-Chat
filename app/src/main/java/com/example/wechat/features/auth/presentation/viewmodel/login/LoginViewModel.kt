package com.example.wechat.features.auth.presentation.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wechat.features.auth.domain.repository.AuthRepository
import com.example.wechat.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
):ViewModel(){
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState


    fun onEvent(e:LoginUiEvent){
        when(e){
            is LoginUiEvent.LoginUser -> {
                loginUser(email = e.email, password = e.password)
            }
        }
    }
    private fun loginUser(email:String,password:String){
        _loginUiState.update {
            it.copy(isLoading = true, error = null, success = null)
        }
        viewModelScope.launch {
            when(val result = authRepository.loginUser(email,password)){
                is Result.Failure -> {
                    _loginUiState.update {
                        it.copy(isLoading = false,
                            error = result.message)
                    }
                }
                is Result.Success -> {
                    _loginUiState.update {
                        it.copy(isLoading = false,
                            success = "Login Successful",
                            user = result.data
                        )
                    }
                }
            }
        }
    }
}