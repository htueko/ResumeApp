package com.htueko.resumeapp.presentation.view.addresume.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.htueko.resumeapp.R
import com.htueko.resumeapp.domain.model.Resume
import com.htueko.resumeapp.presentation.common.component.ButtonPrimary
import com.htueko.resumeapp.presentation.common.component.TextFieldPrimary
import com.htueko.resumeapp.presentation.common.component.VerticalSpacer
import com.htueko.resumeapp.presentation.common.navargs.ResumeNavArgs
import com.htueko.resumeapp.presentation.theme.spacing
import com.htueko.resumeapp.presentation.view.addresume.state.AddResumeUserEvent
import com.htueko.resumeapp.presentation.view.addresume.viewmodel.AddResumeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(
    navArgsDelegate = ResumeNavArgs::class
)
@Composable
fun AddResumeScreen(
    navigator: DestinationsNavigator,
    viewModel: AddResumeViewModel = hiltViewModel()
) {
    // to get the state of the scaffold
    val scaffoldState = rememberScaffoldState()

    // to collect the resume as state
    val data = viewModel.resume.collectAsState().value
    val hasNameError = viewModel.hasNameError.collectAsState().value
    val hasMobileNumberError = viewModel.hasMobileNumberError.collectAsState().value
    val hasEmailAddressError = viewModel.hasEmailAddressError.collectAsState().value
    val hasCareerObjectiveError = viewModel.hasCareerObjectiveError.collectAsState().value
    val hasTotalYearsOfExperienceError =
        viewModel.hasTotalYearsOfExperienceError.collectAsState().value
    val hasAddressError = viewModel.hasAddressError.collectAsState().value


    // string to shows
    val toolbarTitle = data?.name ?: stringResource(id = R.string.resume)
    val textSave = stringResource(id = R.string.save)

    // dimens
    val smallPadding = MaterialTheme.spacing.small

    // main screen
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // navigate to add resume screen
                //navigator.navigate(AddResumeScreenDestination())
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
        // add resume form
        ResumeForm(
            formPadding = smallPadding,
            resume = data,
            onNameChanged = {
                viewModel.onEvent(
                    AddResumeUserEvent.OnNameChanged(it)
                )
            },
            isNameError = hasNameError,
            onAvatarUrlChanged = {
                viewModel.onEvent(
                    AddResumeUserEvent.OnImageUrlChanged(it)
                )
            },
            onMobileNumberChanged = {
                viewModel.onEvent(
                    AddResumeUserEvent.OnMobileNumberChanged(it)
                )
            },
            isMobileNumberError = hasMobileNumberError,
            onEmailAddressChanged = {
                viewModel.onEvent(
                    AddResumeUserEvent.OnEmailAddressChanged(it)
                )
            },
            isEmailAddressError = hasEmailAddressError,
            onCareerObjectiveChanged = {
                viewModel.onEvent(
                    AddResumeUserEvent.OnCareerObjectiveChanged(it)
                )
            },
            isCareerObjectiveError = hasCareerObjectiveError,
            onTotalYearOfExperienceChanged = {
                viewModel.onEvent(
                    AddResumeUserEvent.OnTotalYearsOfExperienceChanged(it)
                )
            },
            isTotalYearOfExperienceError = hasTotalYearsOfExperienceError,
            onAddressChanged = {
                viewModel.onEvent(
                    AddResumeUserEvent.OnAddressChanged(it)
                )
            },
            isAddressError = hasAddressError,
            onSaveClicked = {
                viewModel.onEvent(
                    AddResumeUserEvent.OnSaveClick
                )
            }
        )


    }
}

@Composable
fun ResumeForm(
    formPadding: Dp,
    resume: Resume? = null,
    onNameChanged: (String) -> Unit,
    isNameError: Boolean,
    onAvatarUrlChanged: (String) -> Unit,
    onMobileNumberChanged: (String) -> Unit,
    isMobileNumberError: Boolean,
    onEmailAddressChanged: (String) -> Unit,
    isEmailAddressError: Boolean,
    onCareerObjectiveChanged: (String) -> Unit,
    isCareerObjectiveError: Boolean,
    onTotalYearOfExperienceChanged: (String) -> Unit,
    isTotalYearOfExperienceError: Boolean,
    onAddressChanged: (String) -> Unit,
    isAddressError: Boolean,
    onSaveClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(formPadding)
    ) {

        val name = resume?.name ?: stringResource(id = R.string.name)
        val avatarUrl = resume?.avatarUrl ?: ""
        val mobileNumber = resume?.mobileNumber ?: stringResource(id = R.string.mobileNumber)
        val emailAddress = resume?.emailAddress ?: stringResource(id = R.string.emailAddress)
        val careerObjective =
            resume?.careerObjective ?: stringResource(id = R.string.careerObjective)
        val totalYearsOfExperience =
            resume?.totalYearsOfExperience ?: stringResource(id = R.string.totalYearsOfExperience)
        val address = resume?.address ?: stringResource(id = R.string.residenceAddress)
        val textSave = stringResource(id = R.string.save)
        val errorRequired = stringResource(id = R.string.error_required)
        val errorEmailAddress = stringResource(id = R.string.error_email)
        val errorNumber = stringResource(id = R.string.error_common_number)

        // dimens
        val smallVerticalSpacer = MaterialTheme.spacing.small
        val mediumVerticalSpacer = MaterialTheme.spacing.medium

        VerticalSpacer(height = smallVerticalSpacer)

        // name text field
        VerticalSpacer(height = mediumVerticalSpacer)
        TextFieldPrimary(
            text = name,
            labelText = stringResource(id = R.string.name),
            onTextChanged = onNameChanged,
            hasError = isNameError,
            errorMessage = errorRequired,
        )

        // mobile number text field
        VerticalSpacer(height = mediumVerticalSpacer)
        TextFieldPrimary(
            text = mobileNumber,
            labelText = stringResource(id = R.string.mobileNumber),
            onTextChanged = onMobileNumberChanged,
            hasError = isMobileNumberError,
            errorMessage = errorNumber,
            keyboardType = KeyboardType.Number,
        )

        // email address text field
        VerticalSpacer(height = mediumVerticalSpacer)
        TextFieldPrimary(
            text = emailAddress,
            labelText = stringResource(id = R.string.emailAddress),
            onTextChanged = onEmailAddressChanged,
            hasError = isEmailAddressError,
            errorMessage = errorEmailAddress,
            keyboardType = KeyboardType.Email,
        )

        // career objective text field
        VerticalSpacer(height = mediumVerticalSpacer)
        TextFieldPrimary(
            text = careerObjective,
            labelText = stringResource(id = R.string.careerObjective),
            onTextChanged = onCareerObjectiveChanged,
            hasError = isCareerObjectiveError,
            errorMessage = errorRequired,
        )

        // total year of experience text field
        VerticalSpacer(height = mediumVerticalSpacer)
        TextFieldPrimary(
            text = totalYearsOfExperience.toString(),
            labelText = stringResource(id = R.string.totalYearsOfExperience),
            onTextChanged = onTotalYearOfExperienceChanged,
            hasError = isTotalYearOfExperienceError,
            errorMessage = errorNumber,
            keyboardType = KeyboardType.Number,
        )

        // address text field
        VerticalSpacer(height = mediumVerticalSpacer)
        TextFieldPrimary(
            text = address,
            labelText = stringResource(id = R.string.residenceAddress),
            onTextChanged = onAddressChanged,
            hasError = isAddressError,
            errorMessage = errorRequired,
            imeAction = ImeAction.Done
        )

        // save button
        ButtonPrimary(
            text = textSave,
            onClick = onSaveClicked,
        )

    }
}