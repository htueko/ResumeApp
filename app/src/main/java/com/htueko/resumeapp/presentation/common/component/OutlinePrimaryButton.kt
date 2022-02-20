package com.htueko.resumeapp.presentation.common.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Outline button with icon.
 *
 * @param [modifier] modifier to passed down from parent.
 * @param [borderWidth] width of the border, default is 1 dp.
 * @param [borderColour] colour of the border, default is primary colour.
 * @param [imageVector] icon for the button, default is heart outline.
 * @param [contentDescription] description of the icon.
 * @param [iconPadding] end padding of the icon.
 * @param [text] text for the button.
 * @param [onClick] callback when the button is clicked.
 */
@Composable
fun OutlinePrimaryButton(
    modifier: Modifier = Modifier,
    borderWidth: Dp = 1.dp,
    borderColour: Color = MaterialTheme.colors.primary,
    imageVector: ImageVector = Icons.Outlined.FavoriteBorder,
    contentDescription: String? = null,
    iconPadding: Dp = 4.dp,
    text: String,
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(borderWidth, borderColour),
        modifier = modifier,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier.padding(end = iconPadding)
        )
        Text(text = text)
    }
}
