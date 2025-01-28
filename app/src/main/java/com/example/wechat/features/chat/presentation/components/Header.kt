package com.example.wechat.features.chat.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.wechat.features.home.presentation.util.Story
import com.example.wechat.ui.theme.DMSansMedium

@Composable
fun Header(modifier:Modifier = Modifier,story: Story){
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        val imageState = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(story.profile)
                .size(Size.ORIGINAL)
                .build()).state
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ){
            when(imageState){
                AsyncImagePainter.State.Empty -> TODO()
                is AsyncImagePainter.State.Error -> {
                    Text("Failed to load Image")
                }
                is AsyncImagePainter.State.Loading -> {
                    CircularProgressIndicator(
                        modifier=Modifier.size(20.dp),
                        strokeWidth = 2.dp)
                }
                is AsyncImagePainter.State.Success -> {
                    Image(
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        painter = imageState.painter,
                        contentDescription = "profile")
                }
            }

        }
        Text(
            story.name,
            fontFamily = DMSansMedium,
            fontSize = 16.sp)
    }
}