package com.htueko.resumeapp.presentation.view.addresume.ui

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.htueko.resumeapp.R
import com.htueko.resumeapp.presentation.common.commonstate.CommonUiEvent
import com.htueko.resumeapp.presentation.common.component.ButtonPrimary
import com.htueko.resumeapp.presentation.common.component.RoundAvatarImage
import com.htueko.resumeapp.presentation.common.component.TextFieldPrimary
import com.htueko.resumeapp.presentation.common.component.VerticalSpacer
import com.htueko.resumeapp.presentation.theme.spacing
import com.htueko.resumeapp.presentation.view.addresume.state.AddResumeUserEvent
import com.htueko.resumeapp.presentation.view.addresume.viewmodel.AddResumeViewModel

@Suppress("LongMethod")
@Composable
fun AddResumeScreen(
    onSaveResumeClick: (Int) -> Unit,
    viewModel: AddResumeViewModel = hiltViewModel()
) {
    // to get the state of the scaffold
    val scaffoldState = rememberScaffoldState()

    // to collect the resume as state
    val data by viewModel.resume.collectAsState()
    val hasNameError by viewModel.hasNameError.collectAsState()
    val hasMobileNumberError by viewModel.hasMobileNumberError.collectAsState()
    val hasEmailAddressError by viewModel.hasEmailAddressError.collectAsState()
    val hasCareerObjectiveError by viewModel.hasCareerObjectiveError.collectAsState()
    val hasTotalYearsOfExperienceError by
    viewModel.hasTotalYearsOfExperienceError.collectAsState()
    val hasAddressError by viewModel.hasAddressError.collectAsState()

    val name by viewModel.name.collectAsState()
    val avatarUrl by viewModel.avatarUrl.collectAsState()
    val mobileNumber by viewModel.mobileNumber.collectAsState()
    val emailAddress by viewModel.emailAddress.collectAsState()
    val careerObjective by viewModel.careerObjective.collectAsState()
    val totalYearsOfExperience by viewModel.totalYearsOfExperience.collectAsState()
    val address by viewModel.address.collectAsState()
    val textSave = stringResource(id = R.string.save)
    val errorRequired = stringResource(id = R.string.error_required)
    val errorEmailAddress = stringResource(id = R.string.error_email)
    val errorNumber = stringResource(id = R.string.error_common_number)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        Log.d("avatar", "AddResumeScreen: $uri")
        Log.d("avatar", "AddResumeScreen: $uri")
        viewModel.onEvent(AddResumeUserEvent.OnImageUrlChanged(uri.toString()))
    }

    // string to shows
    val toolbarTitle = data?.name ?: stringResource(id = R.string.resume)
    val errorRequiredFields = stringResource(id = R.string.errorRequiredFields)

    // dimens
    val smallPadding = MaterialTheme.spacing.small
    val imageHeight = 240.dp
    val smallVerticalSpacer = MaterialTheme.spacing.small
    val mediumVerticalSpacer = MaterialTheme.spacing.medium

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                CommonUiEvent.PopBackStack -> {
                    // do nothing here
                }
                CommonUiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = errorRequiredFields,
                    )
                }
                is CommonUiEvent.PopBackStackAndSendData -> {
                    // go back to Detail Resume Screen.
                    onSaveResumeClick(event.resumeId)
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

            // Image view column
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .padding(mediumVerticalSpacer)
                    .clickable { launcher.launch("image/*") },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (avatarUrl.isNotBlank()) {
                    RoundAvatarImage(
                        imageUrl = avatarUrl,
                        contentDescription = data?.name
                    )
                } else {
                    RoundAvatarImage(
                        drawableResId = R.drawable.ic_launcher_foreground
                    )
                }
            }
            VerticalSpacer(height = mediumVerticalSpacer)

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
