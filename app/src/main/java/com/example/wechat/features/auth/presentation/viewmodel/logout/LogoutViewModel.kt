package com.example.wechat.features.auth.presentation.viewmodel.logout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wechat.features.auth.domain.repository.AuthRepository
import com.example.wechat.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LogoutViewModel(
    private val authRepository: AuthRepository
):ViewModel() {
    private val _logoutUiState = MutableStateFlow(LogoutUiState())
    val logoutUiState = _logoutUiState
    val isLoggedIn = authRepository.isLoggedIn

    fun onEvent(e:LogoutUiEvent){
        when(e){
            LogoutUiEvent.Logout -> {
                logout()
            }
        }
    }

    private fun logout(){
        _logoutUiState.update {
            it.copy(isLoading = true, error = null, success = null)
        }
        viewModelScope.launch {
            when(val result = authRepository.logout()){
                is Result.Failure -> {
                    _logoutUiState.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
                is Result.Success -> {
                    _logoutUiState.update {
                        it.copy(isLoading = false, success = "Logout Successful")
                    }
                }
            }
        }
    }
}