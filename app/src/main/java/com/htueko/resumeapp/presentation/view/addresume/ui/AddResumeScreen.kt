package com.htueko.resumeapp.presentation.view.addresume.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.htueko.resumeapp.R
import com.htueko.resumeapp.presentation.common.commonstate.CommonUiEvent
import com.htueko.resumeapp.presentation.common.component.ButtonPrimary
import com.htueko.resumeapp.presentation.common.component.TextFieldPrimary
import com.htueko.resumeapp.presentation.common.component.VerticalSpacer
import com.htueko.resumeapp.presentation.common.navargs.ResumeNavArgs
import com.htueko.resumeapp.presentation.theme.spacing
import com.htueko.resumeapp.presentation.view.addresume.state.AddResumeUserEvent
import com.htueko.resumeapp.presentation.view.addresume.viewmodel.AddResumeViewModel
import com.htueko.resumeapp.presentation.view.destinations.DetailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
fun AddResumeScreen(
    navController: NavController,
    resumeId: Int? = null,
    viewModel: AddResumeViewModel = hiltViewModel()
) {
    // to get the state of the scaffold
    val scaffoldState = rememberScaffoldState()

    // to collect the resume as state
    val data = viewModel.resume.collectAsState()
    val hasNameError = viewModel.hasNameError.collectAsState().value
    val hasMobileNumberError = viewModel.hasMobileNumberError.collectAsState().value
    val hasEmailAddressError = viewModel.hasEmailAddressError.collectAsState().value
    val hasCareerObjectiveError = viewModel.hasCareerObjectiveError.collectAsState().value
    val hasTotalYearsOfExperienceError =
        viewModel.hasTotalYearsOfExperienceError.collectAsState().value
    val hasAddressError = viewModel.hasAddressError.collectAsState().value

    val name = viewModel.name.collectAsState().value
    val avatarUrl = viewModel.avatarUrl.collectAsState().value
    val mobileNumber = viewModel.mobileNumber.collectAsState().value
    val emailAddress = viewModel.emailAddress.collectAsState().value
    val careerObjective = viewModel.careerObjective.collectAsState().value
    val totalYearsOfExperience = viewModel.totalYearsOfExperience.collectAsState().value
    val address = viewModel.address.collectAsState().value
    val textSave = stringResource(id = R.string.save)
    val errorRequired = stringResource(id = R.string.error_required)
    val errorEmailAddress = stringResource(id = R.string.error_email)
    val errorNumber = stringResource(id = R.string.error_common_number)


    // string to shows
    val toolbarTitle = data.value?.name ?: stringResource(id = R.string.resume)
    val errorRequiredFields = stringResource(id = R.string.errorRequiredFields)

    // dimens
    val smallPadding = MaterialTheme.spacing.small
    val smallVerticalSpacer = MaterialTheme.spacing.small
    val mediumVerticalSpacer = MaterialTheme.spacing.medium

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                CommonUiEvent.PopBackStack -> {
                    // go back to Detail Resume Screen.
                    navigator.popBackStack()
                }
                CommonUiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = errorRequiredFields,
                    )
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
                .padding(smallPadding)
                .verticalScroll(rememberScrollState())
        ) {

            // name text field
            VerticalSpacer(height = mediumVerticalSpacer)
            TextFieldPrimary(
                text = name,
                labelText = stringResource(id = R.string.name),
                onTextChanged = {
                    viewModel.onEvent(
                        AddResumeUserEvent.OnNameChanged(it)
                    )
                },
                hasError = hasNameError,
                errorMessage = stringResource(id = R.string.error_required),
                keyboardType = KeyboardType.Text,
            )

            // mobile number text field
            VerticalSpacer(height = mediumVerticalSpacer)
            TextFieldPrimary(
                text = mobileNumber,
                labelText = stringResource(id = R.string.mobileNumber),
                onTextChanged = {
                    viewModel.onEvent(
                        AddResumeUserEvent.OnMobileNumberChanged(it)
                    )
                },
                hasError = hasMobileNumberError,
                errorMessage = errorNumber,
                keyboardType = KeyboardType.Number,
            )

            // email address text field
            VerticalSpacer(height = mediumVerticalSpacer)
            TextFieldPrimary(
                text = emailAddress,
                labelText = stringResource(id = R.string.emailAddress),
                onTextChanged = {
                    println(it)
                    viewModel.onEvent(
                        AddResumeUserEvent.OnEmailAddressChanged(it)
                    )
                },
                hasError = hasEmailAddressError,
                errorMessage = errorEmailAddress,
                keyboardType = KeyboardType.Email,
            )

            // career objective text field
            VerticalSpacer(height = mediumVerticalSpacer)
            TextFieldPrimary(
                text = careerObjective,
                labelText = stringResource(id = R.string.careerObjective),
                onTextChanged = {
                    viewModel.onEvent(
                        AddResumeUserEvent.OnCareerObjectiveChanged(it)
                    )
                },
                hasError = hasCareerObjectiveError,
                errorMessage = errorRequired,
            )

            // total year of experience text field
            VerticalSpacer(height = mediumVerticalSpacer)
            TextFieldPrimary(
                text = totalYearsOfExperience.toString(),
                labelText = stringResource(id = R.string.totalYearsOfExperience),
                onTextChanged = {
                    viewModel.onEvent(
                        AddResumeUserEvent.OnTotalYearsOfExperienceChanged(it)
                    )
                },
                hasError = hasTotalYearsOfExperienceError,
                errorMessage = errorNumber,
                keyboardType = KeyboardType.Number,
            )

            // address text field
            VerticalSpacer(height = mediumVerticalSpacer)
            TextFieldPrimary(
                text = address,
                labelText = stringResource(id = R.string.residenceAddress),
                onTextChanged = {
                    viewModel.onEvent(
                        AddResumeUserEvent.OnAddressChanged(it)
                    )
                },
                hasError = hasAddressError,
                errorMessage = errorRequired,
                imeAction = ImeAction.Done
            )

            // save button
            VerticalSpacer(height = mediumVerticalSpacer)
            ButtonPrimary(
                text = textSave,
                onClick = {
                    viewModel.onEvent(
                        AddResumeUserEvent.OnSaveClick
                    )
                },
            )
            VerticalSpacer(height = mediumVerticalSpacer)
        }

    }
}