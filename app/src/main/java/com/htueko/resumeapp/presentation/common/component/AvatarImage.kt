package com.htueko.resumeapp.presentation.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.htueko.resumeapp.R

@Composable
fun AvatarImage(
    modifier: Modifier,
    alignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    arrangement: Arrangement.HorizontalOrVertical = Arrangement.Center,
    imageAvatar: String,
    contentDescription: String? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = alignment,
        verticalArrangement = arrangement
    ) {
        if (imageAvatar.isNotBlank()) {
            RoundAvatarImage(
                imageUrl = imageAvatar,
                contentDescription = contentDescription,
            )
        } else {
            RoundAvatarImage(
                drawableResId = R.drawable.ic_launcher_foreground
            )
        }
    }
}
