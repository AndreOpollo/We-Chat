package com.example.wechat.features.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.wechat.ui.theme.DMSansRegular

@Composable
fun ChatListItem(
    modifier: Modifier = Modifier,
    story: Story){
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(story.profile)
            .size(Size.ORIGINAL)
            .build()).state
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ){
            when(imageState){
                AsyncImagePainter.State.Empty -> TODO()
                is AsyncImagePainter.State.Error -> {
                    Text("Image failed to load")
                }
                is AsyncImagePainter.State.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp)
                }
                is AsyncImagePainter.State.Success -> {
                    Image(
                        modifier = Modifier
                            .size(55.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        painter = imageState.painter,
                        contentDescription = "chat profile")
                }
            }

         }
            Column(){
                Text(text = story.name,
                    fontFamily = DMSansMedium,
                    fontSize = 15.sp)
                Text(text = story.message!!,
                    fontFamily = DMSansRegular,
                    fontSize = 13.sp,
                    color = Color.Gray)
            }

        }

        Text("02:11",
            fontFamily = DMSansRegular,
            fontSize = 12.sp,
            color = Color.Gray)

    }

}