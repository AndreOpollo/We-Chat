package com.example.wechat.features.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.wechat.features.auth.data.models.User
import com.example.wechat.features.home.presentation.util.Story
import com.example.wechat.ui.theme.DMSansMedium

@Composable
fun StoryListItem(modifier:Modifier = Modifier,user: User){
     val imageState = rememberAsyncImagePainter(
         model = ImageRequest.Builder(LocalContext.current)
             .data(user.photoUrl)
             .size(Size.ORIGINAL)
             .build()
     ).state
    Column(
        modifier = modifier.padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center){
            when(imageState){
                AsyncImagePainter.State.Empty -> TODO()
                is AsyncImagePainter.State.Error -> {
                    Text("Failed to Load")
                }
                is AsyncImagePainter.State.Loading -> {
                    CircularProgressIndicator(
                        modifier=Modifier.size(20.dp),
                        strokeWidth = 2.dp)
                }
                is AsyncImagePainter.State.Success -> {
                    Image(
                        modifier = Modifier.size(55.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        painter = imageState.painter,
                        contentDescription = "story image")

                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            fontFamily = DMSansMedium,
            text = user.username,
            fontSize = 13.sp
        )

    }

}