package com.example.wechat.features.home.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wechat.features.home.presentation.components.FloatingActionBar
import com.example.wechat.features.home.presentation.components.TopBar

@Composable
fun HomeScreen(modifier:Modifier = Modifier){
    Scaffold(
        topBar = { TopBar()},
        content = {paddingValues ->
            Column(
                modifier = modifier.padding(paddingValues)
            ){
                Text("Home Screen")
            }
        },
        floatingActionButton = { FloatingActionBar()}
    )


}