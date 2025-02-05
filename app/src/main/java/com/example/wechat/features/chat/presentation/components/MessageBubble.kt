package com.example.wechat.features.chat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wechat.ui.theme.DMSansRegular
import com.example.wechat.ui.theme.WeChatTheme

@Composable
fun MessageBubble(
    modifier: Modifier = Modifier,
    color: Color,
    text:String
){
    Box(modifier = modifier
        .padding(16.dp)
        .clip(RoundedCornerShape(14.dp))
        .background(color)){
        Text(text,
            fontFamily = DMSansRegular,
            modifier = Modifier.padding(16.dp),
            color = Color.Black)

    }

}

@Preview(showBackground = true)
@Composable
fun MessageBubblePreview(

){
    WeChatTheme {
        MessageBubble(color = Color.Red,text="Hello")
    }
}
