package com.htueko.resumeapp.presentation.view.addproject.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.htueko.resumeapp.R
import com.htueko.resumeapp.presentation.common.commonstate.CommonUiEvent
import com.htueko.resumeapp.presentation.common.component.BodyText
import com.htueko.resumeapp.presentation.common.component.ButtonPrimary
import com.htueko.resumeapp.presentation.common.component.TextFieldPrimary
import com.htueko.resumeapp.presentation.common.component.TitleText
import com.htueko.resumeapp.presentation.common.component.VerticalSpacer
import com.htueko.resumeapp.presentation.theme.spacing
import com.htueko.resumeapp.presentation.view.addproject.state.AddProjectUserEvent
import com.htueko.resumeapp.presentation.view.addproject.viewmodel.AddProjectViewModel

@Suppress("LongMethod")
@Composable
fun AddProjectScreen(
    resumeId: Int? = null,
    onSaveProjectClick: (Int) -> Unit,
    viewModel: AddProjectViewModel = hiltViewModel()
) {
    // to get the state of the scaffold
    val scaffoldState = rememberScaffoldState()

    // to collect the resume as state
    val data by viewModel.resume.collectAsState()
    val projects = data.projects
    val projectName by viewModel.projectName.collectAsState()
    val hasProjectNameError by viewModel.hasProjectNameError.collectAsState()
    val teamSize by viewModel.teamSize.collectAsState()
    val hasTeamSizeError by viewModel.hasTeamSizeError.collectAsState()
    val projectSummary by viewModel.projectSummary.collectAsState()
    val hasProjectSummaryError by viewModel.hasProjectSummaryError.collectAsState()
    val role by viewModel.role.collectAsState()
    val hasRoleError by viewModel.hasRoleError.collectAsState()
    val technology by viewModel.technology.collectAsState()
    val hasTechnologyError by viewModel.hasTechnologyError.collectAsState()

    // string to shows
    val toolbarTitle =
        data.resume.name.replaceFirstChar { it.uppercase() }
    val textProject = stringResource(id = R.string.project)
    val textSave = stringResource(id = R.string.save)
    val errorRequiredFields = stringResource(id = R.string.errorRequiredFields)

    // dimens
    val mediumPadding = MaterialTheme.spacing.medium
    val smallVerticalSpacer = MaterialTheme.spacing.small
    val mediumVerticalSpacer = MaterialTheme.spacing.medium

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                CommonUiEvent.PopBackStack -> {
                    // nothing to do here.
                }
                CommonUiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = errorRequiredFields,
                    )
                }
                is CommonUiEvent.PopBackStackAndSendData -> {
                    onSaveProjectClick(event.resumeId)
                }
            }
        }
    }

    // main screen
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = { Text(text = toolbarTitle) })
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(mediumPadding)
                .verticalScroll(rememberScrollState())
        ) {

            // adding new projects section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(mediumPadding)
                    .verticalScroll(rememberScrollState())
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // project name text field
                VerticalSpacer(height = mediumVerticalSpacer)
                TextFieldPrimary(
                    text = projectName,
                    labelText = stringResource(id = R.string.projectName),
                    onTextChanged = {
                        viewModel.onEvent(
                            AddProjectUserEvent.OnProjectNameChanged(it)
                        )
                    },
                    hasError = hasProjectNameError,
                    errorMessage = stringResource(id = R.string.error_required),
                    keyboardType = KeyboardType.Text,
                )

                // team size text field
                VerticalSpacer(height = mediumVerticalSpacer)
                TextFieldPrimary(
                    text = teamSize,
                    labelText = stringResource(id = R.string.teamSize),
                    onTextChanged = {
                        viewModel.onEvent(
                            AddProjectUserEvent.OnTeamSizeChanged(it)
                        )
                    },
                    hasError = hasTeamSizeError,
                    errorMessage = stringResource(id = R.string.error_required),
                    keyboardType = KeyboardType.Number,
                )

                // project summary text field
                VerticalSpacer(height = mediumVerticalSpacer)
                TextFieldPrimary(
                    text = projectSummary,
                    labelText = stringResource(id = R.string.projectSummary),
                    onTextChanged = {
                        viewModel.onEvent(
                            AddProjectUserEvent.OnProjectSummaryChanged(it)
                        )
                    },
                    hasError = hasProjectSummaryError,
                    errorMessage = stringResource(id = R.string.error_required),
                    keyboardType = KeyboardType.Text,
                )

                // role text field
                VerticalSpacer(height = mediumVerticalSpacer)
                TextFieldPrimary(
                    text = role,
                    labelText = stringResource(id = R.string.role),
                    onTextChanged = {
                        viewModel.onEvent(
                            AddProjectUserEvent.OnRoleChanged(it)
                        )
                    },
                    hasError = hasRoleError,
                    errorMessage = stringResource(id = R.string.role),
                    keyboardType = KeyboardType.Text,
                )

                // technology text field
                VerticalSpacer(height = mediumVerticalSpacer)
                TextFieldPrimary(
                    text = technology,
                    labelText = stringResource(id = R.string.technology),
                    onTextChanged = {
                        viewModel.onEvent(
                            AddProjectUserEvent.OnTechnologyChanged(it)
                        )
                    },
                    hasError = hasTechnologyError,
                    errorMessage = stringResource(id = R.string.error_required),
                    keyboardType = KeyboardType.Text,
                )

                // save button
                VerticalSpacer(height = mediumVerticalSpacer)
                ButtonPrimary(
                    text = textSave,
                    onClick = {
                        viewModel.onEvent(
                            AddProjectUserEvent.OnSaveClick
                        )
                    },
                )
                VerticalSpacer(height = mediumVerticalSpacer)
            }

            // existing projects column
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(mediumPadding)
                    .verticalScroll(rememberScrollState())
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                VerticalSpacer(height = mediumVerticalSpacer)
                // Resume properties section
                TitleText(text = textProject)
                VerticalSpacer(height = smallVerticalSpacer)
                if (projects.isNotEmpty()) {
                    projects.forEach { project ->
                        BodyText(text = project.projectName)
                        BodyText(text = project.teamSize.toString())
                        BodyText(text = project.projectSummary)
                        BodyText(text = project.role)
                        BodyText(text = project.technology)
                        VerticalSpacer(height = smallVerticalSpacer)
                    }
                }
                VerticalSpacer(height = smallVerticalSpacer)
            }
        }
    }
}
