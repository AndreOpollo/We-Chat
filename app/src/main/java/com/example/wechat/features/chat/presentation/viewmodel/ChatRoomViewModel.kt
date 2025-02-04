package com.example.wechat.features.chat.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wechat.features.chat.domain.repository.ChatRoomRepository
import com.example.wechat.util.Result
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatRoomViewModel(
    private val chatRoomRepository: ChatRoomRepository,
    private val firebaseAuth: FirebaseAuth,
    private val receiverId: String
):ViewModel() {
    private val _chatRoomUiState = MutableStateFlow(ChatRoomUiState())
    val chatRoomUiState = _chatRoomUiState.asStateFlow()

    init {
         val senderId = firebaseAuth.currentUser?.uid
        if(senderId!=null){
            fetchChats(senderId,receiverId)
        }

    }

    fun onEvent(e:ChatRoomUiEvent){
        when(e){
            is ChatRoomUiEvent.SendChat -> sendChat(message = e.message, receiverId = e.receiverId)
        }
    }

    private fun fetchChats(senderId:String,receiverId: String){
        _chatRoomUiState.update {
            it.copy(isLoading = true, error = null, success = null)
        }
        viewModelScope.launch {
            chatRoomRepository.fetchChats(senderId,receiverId).collectLatest {
                result->
                when(result){
                    is Result.Failure -> _chatRoomUiState
                        .update { it.copy(isLoading = false,
                            error = result.message) }
                    Result.Loading -> {_chatRoomUiState
                        .update { it.copy(isLoading = true) }
                        println("Fetching...")

                    }
                    is Result.Success -> {_chatRoomUiState
                        .update { it.copy(
                            isLoading = false,
                            messages = result.data,
                            success = "Fetch Successful"
                    )}
                        println("Messages: ${result.data}")
                    }
                }
            }
        }
    }

    private fun sendChat(message:String,receiverId:String){
        _chatRoomUiState.update {
            it.copy(
                isLoading = true, error = null, success = null
            )
        }
        viewModelScope.launch {
            chatRoomRepository.sendChat(message,receiverId).collectLatest { result->
                when(result){
                    is Result.Failure -> {
                        _chatRoomUiState.update {
                            it.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                    Result.Loading -> {
                        _chatRoomUiState.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }
                    is Result.Success -> {
                        _chatRoomUiState.update {
                            it.copy(isLoading = false, success = "Message sent")
                        }
                    }
                }

            }
        }
    }
}