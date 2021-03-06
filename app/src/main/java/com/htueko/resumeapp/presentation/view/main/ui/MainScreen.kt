package com.htueko.resumeapp.presentation.view.main.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.htueko.resumeapp.R
import com.htueko.resumeapp.presentation.common.commonstate.CommonUiEvent
import com.htueko.resumeapp.presentation.view.main.state.DashboardUserEvent
import com.htueko.resumeapp.presentation.view.main.viewmodel.DashboardViewModel

@Suppress("LongMethod")
@ExperimentalMaterialApi
@Composable
fun MainScreen(
    onAddResumeClick: () -> Unit,
    onResumeClick: (Int) -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {

    // to collect the resume list as state
    val resumes by viewModel.resumes.collectAsState()
    // to get the state of the scaffold
    val scaffoldState = rememberScaffoldState()

    // string to shows
    val deletedResumeText = stringResource(id = R.string.deleteResume)
    val undoText = stringResource(id = R.string.undo)
    val addResume = stringResource(id = R.string.addResume)
    val toolbarTitle = stringResource(id = R.string.app_name)

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is CommonUiEvent.ShowSnackBar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = deletedResumeText,
                        actionLabel = undoText,
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(DashboardUserEvent.OnUndoDeleteResumeClick)
                    }
                }
                CommonUiEvent.PopBackStack -> {
                    // nothing to do here
                }
                is CommonUiEvent.PopBackStackAndSendData -> {
                    // nothing to do here
                }
            }
        }
    }
    // main screen
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // navigate to add resume screen
                    // workaround, if the resumeId is -1, that means to add new resume not existing one.
                    onAddResumeClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = addResume
                )
            }
        },
        topBar = {
            TopAppBar(title = { Text(text = toolbarTitle) })
        }
    ) {
        // list of show resumes
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            println(resumes)
            items(resumes) { resume ->
                ResumeItem(
                    resume = resume,
                    onResumeClick = {
                        onResumeClick(resume.resumeId)
                    },
                    onDeleteResumeClick = {
                        viewModel.onEvent(
                            DashboardUserEvent.OnDeleteResumeClick(it)
                        )
                    }
                )
            }
        }
    }
}
