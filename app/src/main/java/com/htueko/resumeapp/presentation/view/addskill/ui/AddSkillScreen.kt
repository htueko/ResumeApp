package com.htueko.resumeapp.presentation.view.addskill.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.htueko.resumeapp.R
import com.htueko.resumeapp.presentation.common.component.SecondaryTextField
import com.htueko.resumeapp.presentation.common.navargs.ResumeNavArgs
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(
    navArgsDelegate = ResumeNavArgs::class
)
@Composable
fun AddSkillScreen(
    navigator: DestinationsNavigator,
) {
// to get the state of the scaffold
    val scaffoldState = rememberScaffoldState()

    // string to shows
    val toolbarTitle = stringResource(id = R.string.skill)
    val textSave = stringResource(id = R.string.save)

    // main screen
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = { Text(text = toolbarTitle) })
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

        }
    }
}


