package com.example.wechat.features.auth.presentation.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wechat.R
import com.example.wechat.features.auth.presentation.components.CustomButton
import com.example.wechat.features.auth.presentation.components.TextInputField
import com.example.wechat.ui.theme.DMSansBold
import com.example.wechat.ui.theme.Tertiary
import com.example.wechat.ui.theme.WeChatTheme

@Composable
fun LoginScreen(modifier: Modifier = Modifier){
    val scrollState = rememberScrollState()
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
            value = "",
            onValueChange = {},
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
            value = "",
            onValueChange = {},
            placeholder = {
                Text("Password")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "username")
            })
        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(onClick = { /*TODO*/ }, title = "Register")
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
                modifier = Modifier.imePadding())
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    WeChatTheme {
        LoginScreen()
    }
}