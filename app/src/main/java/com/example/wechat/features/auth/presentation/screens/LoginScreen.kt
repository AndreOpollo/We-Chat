package com.example.wechat.features.auth.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wechat.R
import com.example.wechat.features.auth.presentation.components.CustomButton
import com.example.wechat.features.auth.presentation.components.TextInputField
import com.example.wechat.features.auth.presentation.viewmodel.login.LoginUiEvent
import com.example.wechat.features.auth.presentation.viewmodel.login.LoginViewModel
import com.example.wechat.ui.theme.DMSansBold
import com.example.wechat.ui.theme.Tertiary
import com.example.wechat.ui.theme.WeChatTheme
import org.koin.androidx.compose.koinViewModel
import kotlin.math.log

@Composable
fun LoginScreen(modifier: Modifier = Modifier
                ,onRegisterClicked:()->Unit,
                onSuccess:()->Unit){
    val scrollState = rememberScrollState()
    val loginViewModel: LoginViewModel = koinViewModel()
    val loginUiState by loginViewModel.loginUiState.collectAsStateWithLifecycle()

    var email by remember {
        mutableStateOf("")
    }
    var password by remember{
        mutableStateOf("")
    }
    LaunchedEffect(key1 = loginUiState.success) {
        if(loginUiState.success!=null){
            onSuccess()
            loginViewModel.resetLoginState()
    }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp, start = 4.dp, end = 4.dp)
            .systemBarsPadding()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Box(
            modifier = Modifier
                .size(250.dp),
            contentAlignment = Alignment.Center
        ){
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "avatar")

        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Sign In",
            fontFamily = DMSansBold,
            fontSize = 30.sp)
        Spacer(modifier = Modifier.height(8.dp))
        TextInputField(
            value = email,
            onValueChange = {email=it},
            placeholder = {
                Text("Email")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "username")
            })
        Spacer(modifier = Modifier.height(8.dp))
        TextInputField(
            value = password,
            onValueChange = {password=it},
            placeholder = {
                Text("Password")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "username")
            })
        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(onClick = {
            loginViewModel.onEvent(LoginUiEvent.LoginUser(email = email,
                password = password))
        }, title = "Login")
        when{
            loginUiState.isLoading -> CircularProgressIndicator()
            loginUiState.error!=null ->Text(loginUiState.error.toString(),
                color = Color.Red,
                fontFamily = DMSansBold)
            loginUiState.success!=null->{
                Text(loginUiState.success.toString(),
                    color = Tertiary,
                    fontFamily = DMSansBold)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text("Don't Have an Account?")
            Spacer(modifier = Modifier.width(4.dp))
            Text("Register",
                color = Tertiary,
                fontFamily = DMSansBold,
                modifier = Modifier
                    .imePadding()
                    .clickable {
                        onRegisterClicked()
                    })
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    WeChatTheme {
        LoginScreen(onRegisterClicked = {}, onSuccess = {})
    }
}