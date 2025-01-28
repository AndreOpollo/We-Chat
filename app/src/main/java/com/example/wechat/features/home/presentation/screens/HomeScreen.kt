package com.example.wechat.features.home.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wechat.features.home.presentation.components.FloatingActionBar
import com.example.wechat.features.home.presentation.components.TopBar
import androidx.compose.material3.IconButton
import androidx.compose.ui.unit.dp
import com.example.wechat.features.home.presentation.components.StoryListItem
import com.example.wechat.features.home.presentation.util.storyList

@Composable
fun HomeScreen(modifier:Modifier = Modifier.padding(horizontal = 10.dp, vertical = 16.dp)){
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
            }
        },
        floatingActionButton = { FloatingActionBar()}
    )


}