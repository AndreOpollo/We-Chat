package com.example.wechat.features.chat.presentation.screens


import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wechat.features.auth.data.models.User
import com.example.wechat.features.chat.presentation.components.TopBar
import com.example.wechat.features.chat.presentation.viewmodel.ChatRoomUiEvent
import com.example.wechat.features.chat.presentation.viewmodel.ChatRoomViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoomScreen(
    modifier: Modifier = Modifier,
    onBackPressed:()->Unit,
    user: User
){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val scrollState = rememberLazyListState()
    val chatRoomViewModel: ChatRoomViewModel = koinViewModel(parameters = { parametersOf(user.id) })
    val chatRoomUiState by chatRoomViewModel.chatRoomUiState.collectAsStateWithLifecycle()
    var text by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = chatRoomUiState.messages) {
        Log.d("messages",chatRoomUiState.messages.toString())
    }
    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = { TopBar(scrollBehavior,
            onBackPressed,
            user)},
        content = {paddingValues->
            Column(modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
                ){
                AnimatedVisibility(visible = chatRoomUiState.isLoading) {
                    CircularProgressIndicator()
                }
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .imePadding(),
                    state = scrollState,
                ){
                    items(chatRoomUiState.messages){message->
                            Text(message.text, color = Color.Black, modifier = Modifier.padding(8.dp))
                    }




                }
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    shape = RoundedCornerShape(30.dp),
                    value = text,
                    onValueChange ={text = it},
                    placeholder = {
                        Text("New Chat")
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            if(text.isNotBlank()) {
                                chatRoomViewModel.onEvent(ChatRoomUiEvent.SendChat(text, user.id))
                                text = ""
                            } }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Send,
                                contentDescription = "send")
                        }
                    }
                )
            }
        }
    )

}