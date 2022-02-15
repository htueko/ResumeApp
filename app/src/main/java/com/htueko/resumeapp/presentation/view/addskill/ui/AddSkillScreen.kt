package com.htueko.resumeapp.presentation.view.addskill.ui

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.htueko.resumeapp.presentation.view.destinations.AddResumeScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.htueko.resumeapp.R

@Destination
@Composable
fun AddSkillScreen(
    navigator: DestinationsNavigator
) {
// to get the state of the scaffold
    val scaffoldState = rememberScaffoldState()

    // string to shows
    val toolbarTitle = stringResource(id = R.string.skill)
    val textSave = stringResource(id = R.string.save)

    // main screen
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // navigate to add resume screen
                navigator.navigate(AddResumeScreenDestination())
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = textSave
                )
            }
        },
        topBar = {
            TopAppBar(title = { Text(text = toolbarTitle) })
        }
    ){

    }
}