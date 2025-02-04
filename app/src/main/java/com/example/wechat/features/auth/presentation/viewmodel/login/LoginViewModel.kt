package com.example.wechat.features.auth.presentation.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wechat.features.auth.domain.repository.AuthRepository
import com.example.wechat.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
):ViewModel(){
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()

    fun onEvent(e:LoginUiEvent){
        when(e){
            is LoginUiEvent.LoginUser -> {
                loginUser(email = e.email, password = e.password)
            }
        }
    }
    fun resetLoginState(){
        _loginUiState.update {
            it.copy(error = null, success = null)
        }
    }

    private fun loginUser(email:String,password:String){
        _loginUiState.update {
            it.copy(isLoading = true, error = null, success = null)
        }
        viewModelScope.launch {
            authRepository.loginUser(email,password).collectLatest { result->
                when(result){
                    is Result.Failure -> {
                        _loginUiState.update {
                            it.copy(isLoading = false, error = result.message)
                        }
                    }
                    Result.Loading -> {
                        _loginUiState.update {
                            it.copy(isLoading = true)
                        }
                    }
                    is Result.Success -> {
                        _loginUiState.update {
                            it.copy(isLoading = false, success = "Successful login")
                        }
                    }
                }

            }
        }
    }
}