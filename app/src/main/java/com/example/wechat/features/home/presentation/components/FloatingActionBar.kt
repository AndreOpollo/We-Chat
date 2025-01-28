package com.example.wechat.features.home.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.wechat.ui.theme.Primary
import com.example.wechat.ui.theme.Tertiary
import com.example.wechat.ui.theme.WeChatTheme

@Composable
fun FloatingActionBar(onClick:()->Unit){
    FloatingActionButton(onClick = onClick , containerColor = Tertiary, contentColor = Primary)
    {
        Icon(imageVector = Icons.Default.Add, contentDescription = "New Chat")
    }
}

@Preview(showBackground = true)
@Composable
fun FloatingActionPreview(){
    WeChatTheme {
        FloatingActionBar(onClick = {})
    }
}