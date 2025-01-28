package com.example.wechat.features.home.presentation.screens

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wechat.features.home.presentation.components.FloatingActionBar
import com.example.wechat.features.home.presentation.components.TopBar
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wechat.features.home.presentation.components.ChatListItem
import com.example.wechat.features.home.presentation.components.StoryListItem
import com.example.wechat.features.home.presentation.util.chatList
import com.example.wechat.features.home.presentation.util.storyList
import com.example.wechat.ui.theme.DMSansBold

@Composable
fun HomeScreen(
    modifier:Modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
    onClick:()->Unit){
    Scaffold(
        topBar = { TopBar()},
        content = {paddingValues ->
            Column(
                modifier = modifier.padding(paddingValues)
            ){
                LazyRow(){
//                    item{
//                        IconButton(onClick = { /*TODO*/ }) {
//                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Story" )
//                        }
//                    }
                    items(storyList){story->
                        StoryListItem(story = story)
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
                    items(chatList){chat->
                        ChatListItem(story = chat)
                    }
                }
            }
        },
        floatingActionButton = { FloatingActionBar(onClick = onClick)}
    )


}