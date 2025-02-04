package com.example.wechat.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wechat.features.auth.data.models.User
import com.example.wechat.features.auth.presentation.screens.LoginScreen
import com.example.wechat.features.auth.presentation.screens.RegisterScreen
import com.example.wechat.features.chat.presentation.screens.ChatRoomScreen
import com.example.wechat.features.home.presentation.screens.HomeScreen
import kotlinx.serialization.json.Json


@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.RegisterScreen.route ) {
        composable(Screens.LoginScreen.route){
            LoginScreen(onRegisterClicked = {
                navController.popBackStack()
            },
                onSuccess = {
                    navController.navigate(Screens.HomeScreen.route)
                })
        }
        composable(Screens.RegisterScreen.route){
            RegisterScreen(onLoginClicked = {
                navController.navigate(Screens.LoginScreen.route)
            })
        }
        composable(Screens.HomeScreen.route){
            HomeScreen(onSuccessLogout = {
                navController.popBackStack()
            },
                onUserClicked = {user: User ->
                    val userJson = Json.encodeToString(user)
                    val encodedUser = Uri.encode(userJson)
                    navController.navigate("${Screens.ChatRoomScreen.route}/${encodedUser}")
                })

        }
        composable(
            route = "${Screens.ChatRoomScreen.route}/{user}",
            arguments = listOf(
                navArgument("user"){
                    type = NavType.StringType
                }
            )
        ){backStackEntry ->
            val userJson = Uri.decode(backStackEntry.arguments?.getString("user") ?: "")
            val user = Json.decodeFromString<User>(userJson)
            ChatRoomScreen(onBackPressed = {
                navController.popBackStack()
            },
                user = user)
        }
    }
}