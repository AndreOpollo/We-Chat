package com.example.wechat.features.auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.wechat.ui.theme.DMSansMedium
import com.example.wechat.ui.theme.Tertiary


@Composable
fun CustomButton(
    modifier:Modifier = Modifier,
    onClick:()->Unit,
    title:String
){
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .clip(CircleShape),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black,
            containerColor = Tertiary
        ),
        onClick = onClick) {
        Text(
            fontFamily = DMSansMedium,
            text = title,
            textAlign = TextAlign.Center
            )
    }
}