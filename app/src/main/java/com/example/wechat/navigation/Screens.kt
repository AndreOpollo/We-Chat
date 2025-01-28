package com.example.wechat.navigation

sealed class Screens(val route: String){
    data object HomeScreen:Screens("Home")
    data object ChatRoomScreen:Screens("ChatRoom")
}