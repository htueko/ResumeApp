package com.htueko.resumeapp.presentation.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.htueko.resumeapp.R

/**
 * Image view with round corner and border.
 *
 * @param [imageUrl] url of the image.
 * @param [contentDescription] description of the image.
 * @param [contentScale] scale of the image, default is crop.
 * @param [size] size of the image, default is 200 do.
 */
@Composable
fun RoundAvatarImage(
    imageUrl: String? = null,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    size: Dp = 200.dp
) {
    val painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            placeholder(R.drawable.ic_launcher_foreground)
        }
    )
    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .border(2.dp, Color.Gray, CircleShape)
    )

}