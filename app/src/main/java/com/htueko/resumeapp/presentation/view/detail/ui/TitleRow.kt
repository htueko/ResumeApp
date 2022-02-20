package com.htueko.resumeapp.presentation.view.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.htueko.resumeapp.presentation.common.component.OutlinePrimaryButton
import com.htueko.resumeapp.presentation.common.component.TitleText

@Composable
fun TitleRow(
    titleText: String,
    buttonText: String,
    contentDescription: String? = null,
    imageVector: ImageVector = Icons.Outlined.AddCircle,
    onButtonClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        TitleText(
            text = titleText,
            modifier = Modifier.alignByBaseline()
        )
        OutlinePrimaryButton(
            modifier = Modifier.alignByBaseline(),
            text = buttonText,
            contentDescription = contentDescription,
            imageVector = imageVector,
            onClick = onButtonClick
        )
    }
}