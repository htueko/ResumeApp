package com.htueko.resumeapp.presentation.view.addproject.ui

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.htueko.resumeapp.R


@Composable
fun AddProjectScreen(
    navController: NavController,
    resumeId: Int? = null,
) {
// to get the state of the scaffold
    val scaffoldState = rememberScaffoldState()

    // string to shows
    val toolbarTitle = stringResource(id = R.string.project)
    val textSave = stringResource(id = R.string.save)

    // main screen
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // navigate to add resume screen
                // navigator.navigate(AddResumeScreenDestination())
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
    ) {

    }
}