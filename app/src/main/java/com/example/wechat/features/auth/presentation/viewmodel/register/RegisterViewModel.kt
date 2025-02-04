package com.example.wechat.features.auth.presentation.viewmodel.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wechat.features.auth.domain.repository.AuthRepository
import com.example.wechat.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authRepository: AuthRepository
):ViewModel(){
  private val _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState = _registerUiState.asStateFlow()

    fun onEvent(e:RegisterUiEvent){
        when(e){
            is RegisterUiEvent.RegisterUser -> {
                register(
                    email = e.email,
                    password = e.password,
                    username = e.username,
                    photoUrl = e.photoUrl)
            }
        }
    }

    private fun register(email:String,password:String,username:String,photoUrl:String){
        _registerUiState.update {
            it.copy(isLoading = true,error = null, success = null)
        }
        viewModelScope.launch {
         authRepository.createUser(email, username, password, photoUrl).collectLatest { result->
             when(result){
                 is Result.Failure -> {
                     _registerUiState.update {
                         it.copy(isLoading = false, error = result.message)
                     }
                 }
                 Result.Loading -> {
                     _registerUiState.update {
                         it.copy(isLoading = true)
                     }
                 }
                 is Result.Success -> {
                     _registerUiState.update {
                         it.copy(isLoading = false, success = "Successful registration")
                     }
                 }
             }

         }
        }

    }
}