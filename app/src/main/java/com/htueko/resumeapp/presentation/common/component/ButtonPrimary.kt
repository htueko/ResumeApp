package com.htueko.resumeapp.presentation.common.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
 * Custom button for primary button
 *
 * @param [modifier] An optional modifier for this button
 * @param [text] Text of the button
 * @param [onClick] onClick lambda to perform when this button is clicked
 * @param [backgroundColour] colour of the button in enable state
 * @param [textColour] colour of the text of this button in enable state
 */
@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    isEnable: Boolean = true,
    backgroundColour: Color = MaterialTheme.colors.primary,
    textColour: Color = MaterialTheme.colors.onPrimary,
) {
    // colour of the button
    val buttonColors = buttonColors(
        backgroundColor = backgroundColour,
        contentColor = textColour,
    )
    // height of the button
    val buttonHeight = dimensionResource(id = R.dimen.button_height)
    // shape of the button
    val roundCornerShape = MaterialTheme.shapes.medium
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(buttonHeight),
        shape = roundCornerShape,
        colors = buttonColors,
        onClick = onClick,
        enabled = isEnable,
    ) {
        Text(
            text = text.toUpperCase(Locale.current),
        )
    }
}

/**
 * preview of primary button on Dark and Light Theme
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
private fun ButtonPrimaryPreview() {
    ResumeAppTheme() {
        Surface() {
            ButtonPrimary(
                text = "Primary Button",
                onClick = {},
            )
        }
    }
}
