package com.example.wechat.features.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wechat.features.auth.domain.repository.AuthRepository
import com.example.wechat.features.home.domain.repository.HomeRepository
import com.example.wechat.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepository: HomeRepository,
    private val authRepository: AuthRepository
):ViewModel() {
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        fetchUsers()
        fetchUsersWithChats()
    }

    fun fetchUsersWithChats(){
        _homeUiState.update {
            it.copy(isLoading = true, error = null, success = null)
        }
        viewModelScope.launch {
            homeRepository.getAllUsersWithActiveChats().collectLatest {
                result->
                when(result){
                    is Result.Failure -> {
                        _homeUiState.update {
                            it.copy(isLoading = false, error = result.message)
                        }
                    }
                    Result.Loading -> {
                        _homeUiState.update {
                            it.copy(isLoading = true)
                        }
                    }
                    is Result.Success -> {
                        _homeUiState.update {
                            it.copy(isLoading = false, success = "Successful fetch", users = result.data)
                        }
                    }
                }
            }
        }
    }

    fun fetchUsers(){
        _homeUiState.update {
            it.copy(isLoading = true, error = null, success = null)
        }
        viewModelScope.launch {
            homeRepository.getAllUsers().collectLatest {result->
                when(result){
                    is Result.Failure -> {
                        _homeUiState.update {
                            it.copy(isLoading = false, error = result.message)
                        }
                    }
                    is Result.Success -> {
                        _homeUiState.update {
                            it.copy(isLoading = false,
                                success = "Successful",
                                users = result.data)
                        }
                        Log.d("HomeViewModel", "Users fetched: ${result.data}")

                    }

                    Result.Loading -> {
                        _homeUiState.update {
                            it.copy(isLoading = true)
                        }
                    }
                }
            }
        }
    }
    fun resetLogoutState() {
        _homeUiState.update { it.copy(isLoggedOut = false) }
    }
    fun logout(){
        _homeUiState.update {
            it.copy(isLoading = true, error = null, success = null)
        }
        viewModelScope.launch {
            try {
                _homeUiState.update {
                    it.copy(isLoading = false, isLoggedOut = true)
                }
                authRepository.logout()

            }catch (e:Exception){
                _homeUiState.update {
                    it.copy(isLoading = false, error = e.localizedMessage)
                }
            }
        }
    }
}