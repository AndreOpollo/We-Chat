package com.example.wechat.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wechat.features.home.domain.repository.HomeRepository
import com.example.wechat.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepository: HomeRepository
):ViewModel() {
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState

    init {
        fetchUsers()
    }

    private fun fetchUsers(){
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
                    }
                }
            }
        }
    }
}