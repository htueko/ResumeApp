package com.htueko.resumeapp.presentation.common.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * title text with bold colour
 *
 * @param [modifier] Modifier.
 * @param [text] string for the text.
 * @param [textColour] colour of the text, default is onPrimary.
 */
@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    text: String,
    textColour: Color = MaterialTheme.colors.onPrimary
) {
    Text(
        text = text,
        color = textColour,
        style = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        ),
    )
}