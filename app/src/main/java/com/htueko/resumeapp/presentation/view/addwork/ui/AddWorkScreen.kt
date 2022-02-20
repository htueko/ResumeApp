package com.htueko.resumeapp.presentation.view.addwork.ui

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
import com.htueko.resumeapp.presentation.view.addwork.state.AddWorkUserEvent
import com.htueko.resumeapp.presentation.view.addwork.viewmodel.AddWorkViewModel

@Composable
fun AddWorkScreen(
    resumeId: Int? = null,
    onSaveWorkClick: (Int) -> Unit,
    viewModel: AddWorkViewModel = hiltViewModel()
) {
    // to get the state of the scaffold
    val scaffoldState = rememberScaffoldState()

    // to collect the resume as state
    val data by viewModel.resume.collectAsState()
    val works = data.works
    val companyName by viewModel.companyName.collectAsState()
    val hasCompanyNameError by viewModel.hasCompanyNameError.collectAsState()
    val duration by viewModel.duration.collectAsState()
    val hasDurationError by viewModel.hasDurationError.collectAsState()

    // string to shows
    val toolbarTitle =
        data.resume.name.replaceFirstChar { it.uppercase() }
    val textWork = stringResource(id = R.string.work)
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
                    onSaveWorkClick(event.resumeId)
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

            // adding new works section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(mediumPadding)
                    .verticalScroll(rememberScrollState())
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // company name text field
                VerticalSpacer(height = mediumVerticalSpacer)
                TextFieldPrimary(
                    text = companyName,
                    labelText = stringResource(id = R.string.companyName),
                    onTextChanged = {
                        viewModel.onEvent(
                            AddWorkUserEvent.OnCompanyNameChanged(it)
                        )
                    },
                    hasError = hasCompanyNameError,
                    errorMessage = stringResource(id = R.string.error_required),
                    keyboardType = KeyboardType.Text,
                )

                // duration text field
                VerticalSpacer(height = mediumVerticalSpacer)
                TextFieldPrimary(
                    text = duration,
                    labelText = stringResource(id = R.string.duration),
                    onTextChanged = {
                        viewModel.onEvent(
                            AddWorkUserEvent.OnDurationChanged(it)
                        )
                    },
                    hasError = hasDurationError,
                    errorMessage = stringResource(id = R.string.error_required),
                    keyboardType = KeyboardType.Number,
                )

                // save button
                VerticalSpacer(height = mediumVerticalSpacer)
                ButtonPrimary(
                    text = textSave,
                    onClick = {
                        viewModel.onEvent(
                            AddWorkUserEvent.OnSaveClick
                        )
                    },
                )
                VerticalSpacer(height = mediumVerticalSpacer)
            }

            // existing works column
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
                TitleText(text = textWork)
                VerticalSpacer(height = smallVerticalSpacer)
                if (works.isNotEmpty()) {
                    works.forEach { work ->
                        BodyText(text = work.companyName)
                        BodyText(text = "${work.duration} months")
                        VerticalSpacer(height = smallVerticalSpacer)
                    }
                }
                VerticalSpacer(height = smallVerticalSpacer)
            }
        }
    }
}
