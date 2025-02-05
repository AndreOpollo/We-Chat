package com.example.wechat.features.home.presentation.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wechat.features.home.presentation.components.FloatingActionBar
import com.example.wechat.features.home.presentation.components.TopBar
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wechat.features.auth.data.models.User
import com.example.wechat.features.home.presentation.components.ChatListItem
import com.example.wechat.features.home.presentation.components.StoryListItem
import com.example.wechat.features.home.presentation.util.chatList
import com.example.wechat.features.home.presentation.viewmodel.HomeViewModel
import com.example.wechat.ui.theme.DMSansBold
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier:Modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
    onSuccessLogout:()->Unit,
    onUserClicked:(User)->Unit){
    val homeViewModel:HomeViewModel = koinViewModel()
    val homeUiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()


    LaunchedEffect(key1 = homeUiState.isLoggedOut) {
        if(homeUiState.isLoggedOut){
            onSuccessLogout()
            homeViewModel.resetLogoutState()
        }

    }

    Scaffold(
        topBar = { TopBar()},
        content = {paddingValues ->
            Column(
                modifier = modifier.padding(paddingValues).fillMaxWidth()
            ){
                AnimatedVisibility(visible = homeUiState.isLoading) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator()


                    }
                }
                AnimatedVisibility(visible = homeUiState.users.isNotEmpty()) {
                    LazyRow(){

                        items(homeUiState.users){user->
                            StoryListItem(
                                user= user,
                                onUserClicked = onUserClicked)
                        }
                    }

                }
                Spacer(modifier = Modifier.height(16.dp) )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text("Chats",
                        fontFamily = DMSansBold,
                        fontSize = 22.sp)
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.AutoMirrored.Rounded.List,
                            contentDescription = "More")
                    }
                }

                LazyColumn(
                    modifier = Modifier.padding(end = 3.dp)
                ){
                    items(homeUiState.users){user->
                        ChatListItem(user = user, onUserClicked = onUserClicked)
                    }
                }
            }
        },
        floatingActionButton = { FloatingActionBar(onClick = {
           homeViewModel.logout()

        })}

    )


}