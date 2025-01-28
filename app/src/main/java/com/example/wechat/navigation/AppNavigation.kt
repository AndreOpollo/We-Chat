package com.example.wechat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wechat.features.chat.presentation.screens.ChatRoomScreen
import com.example.wechat.features.home.presentation.screens.HomeScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route ) {
        composable(Screens.HomeScreen.route){
            HomeScreen(onClick = {navController.navigate(Screens.ChatRoomScreen.route)})
        }
        composable(Screens.ChatRoomScreen.route){
            ChatRoomScreen()
        }
    }
}