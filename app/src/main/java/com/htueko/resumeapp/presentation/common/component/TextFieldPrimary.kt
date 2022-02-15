package com.htueko.resumeapp.presentation.common.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.htueko.resumeapp.R
import com.htueko.resumeapp.presentation.theme.ResumeAppTheme

/**
 * Custom text field for primary usage
 *
 * @param [modifier] An optional modifier for this button
 * @param [text] Text of the text field like hint
 * @param [labelText] Label of this text field
 * @param [onTextChanged] lambda operation when the user typed string
 */
@Composable
fun TextFieldPrimary(
    modifier: Modifier = Modifier,
    text: String,
    labelText: String,
    onTextChanged: (String) -> Unit,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    Column {
        // height of the text field
        val textFieldHeight = dimensionResource(id = R.dimen.button_height)
        // shape of the text field
        val textFieldShape = MaterialTheme.shapes.medium
        OutlinedTextField(
            value = text,
            label = {
                Text(text = labelText)
            },
            shape = textFieldShape,
            onValueChange = onTextChanged,
            modifier = modifier
                .fillMaxWidth()
                .height(textFieldHeight),
        )
        // error message
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                modifier = Modifier
                    .padding(
                        top = 4.dp,
                        start = 16.dp,
                    ),
            )
        }
    }
}

/**
 * preview of text field primary on Dark and Light Theme
 */
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Suppress("UnusedPrivateMember")
@Composable
private fun TextFieldPrimaryPreview() {
    ResumeAppTheme {
        Surface {
            TextFieldPrimary(
                text = "Text Field",
                labelText = "Label",
                onTextChanged = {},
            )
        }
    }
}
