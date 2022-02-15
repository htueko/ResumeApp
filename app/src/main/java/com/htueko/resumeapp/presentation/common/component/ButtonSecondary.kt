package com.htueko.resumeapp.presentation.common.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ButtonDefaults.textButtonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import com.htueko.resumeapp.R
import com.htueko.resumeapp.presentation.theme.ResumeAppTheme

/**
 * Custom button for secondary text button
 *
 * @param [modifier] An optional modifier for this button
 * @param [text] Text of the button
 * @param [onClick] onClick lambda to perform when this button is clicked
 */
@Composable
fun ButtonSecondary(
    modifier: Modifier = Modifier,
    text: String,
    textColour: Color = MaterialTheme.colors.primary,
    onClick: () -> Unit,
) {
    // colour of the button
    val buttonColors = textButtonColors(
        contentColor = textColour,
    )
    // height of the button
    val buttonHeight = dimensionResource(id = R.dimen.button_height)
    // shape of the button
    val roundCornerShape = MaterialTheme.shapes.medium
    TextButton(
        modifier = modifier
            .fillMaxWidth()
            .height(buttonHeight),
        shape = roundCornerShape,
        colors = buttonColors,
        onClick = onClick,
    ) {
        Text(
            text = text.toUpperCase(Locale.current),
        )
    }
}

/**
 * preview of secondary button on Dark and Light Theme
 */
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun ButtonSecondaryPreview() {
    ResumeAppTheme {
        Surface() {
            ButtonSecondary(
                text = "Secondary Button",
                onClick = {},
            )
        }
    }
}
