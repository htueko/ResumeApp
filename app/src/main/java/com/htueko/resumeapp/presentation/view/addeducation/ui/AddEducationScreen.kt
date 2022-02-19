package com.htueko.resumeapp.presentation.view.addeducation.ui

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
import com.htueko.resumeapp.presentation.view.addeducation.state.AddEducationUserEvent
import com.htueko.resumeapp.presentation.view.addeducation.viewmodel.AddEducationViewModel


@Composable
fun AddEducationScreen(
    resumeId: Int? = null,
    onSaveEducationClick: (Int) -> Unit,
    viewModel: AddEducationViewModel = hiltViewModel()
) {
    // to get the state of the scaffold
    val scaffoldState = rememberScaffoldState()

    // to collect the resume as state
    val data by viewModel.resume.collectAsState()
    val educations = data.educations
    val schoolName by viewModel.schoolName.collectAsState()
    val hasSchoolNameError by viewModel.hasSchoolNameError.collectAsState()
    val passingYear by viewModel.passingYear.collectAsState()
    val hasPassingYear by viewModel.hasPassingYearError.collectAsState()
    val percentageOrCgpa by viewModel.percentageOrCgpa.collectAsState()
    val hasPercentageOrCgpaError by viewModel.hasPercentageOrCgpaError.collectAsState()

    // string to shows
    val toolbarTitle =
        data.resume.name.replaceFirstChar { it.uppercase() }
    val textEducation = stringResource(id = R.string.education)
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
                    onSaveEducationClick(event.resumeId)
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

            // adding new educations section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(mediumPadding)
                    .verticalScroll(rememberScrollState())
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // school name text field
                VerticalSpacer(height = mediumVerticalSpacer)
                TextFieldPrimary(
                    text = schoolName,
                    labelText = stringResource(id = R.string.schoolClass),
                    onTextChanged = {
                        viewModel.onEvent(
                            AddEducationUserEvent.OnSchoolNameChanged(it)
                        )
                    },
                    hasError = hasSchoolNameError,
                    errorMessage = stringResource(id = R.string.error_required),
                    keyboardType = KeyboardType.Text,
                )

                // passing year text field
                VerticalSpacer(height = mediumVerticalSpacer)
                TextFieldPrimary(
                    text = passingYear,
                    labelText = stringResource(id = R.string.passingYear),
                    onTextChanged = {
                        viewModel.onEvent(
                            AddEducationUserEvent.OnPassingYearChanged(it)
                        )
                    },
                    hasError = hasPassingYear,
                    errorMessage = stringResource(id = R.string.error_required),
                    keyboardType = KeyboardType.Number,
                )

                // percentage or cgpa text field
                VerticalSpacer(height = mediumVerticalSpacer)
                TextFieldPrimary(
                    text = percentageOrCgpa,
                    labelText = stringResource(id = R.string.percentageOrCgpa),
                    onTextChanged = {
                        viewModel.onEvent(
                            AddEducationUserEvent.OnPercentageOrCgpaChanged(it)
                        )
                    },
                    hasError = hasPercentageOrCgpaError,
                    errorMessage = stringResource(id = R.string.error_required),
                    keyboardType = KeyboardType.Text,
                )

                // save button
                VerticalSpacer(height = mediumVerticalSpacer)
                ButtonPrimary(
                    text = textSave,
                    onClick = {
                        viewModel.onEvent(
                            AddEducationUserEvent.OnSaveClick
                        )
                    },
                )
                VerticalSpacer(height = mediumVerticalSpacer)

            }

            // existing educations column
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
                TitleText(text = textEducation)
                VerticalSpacer(height = smallVerticalSpacer)
                if (educations.isNotEmpty()) {
                    educations.forEach { education ->
                        BodyText(text = education.schoolClass)
                        BodyText(text = "${education.passingYear} years")
                        BodyText(text = "${education.percentageOrCgpa} percentage or CGPA")
                        VerticalSpacer(height = smallVerticalSpacer)
                    }
                }
                VerticalSpacer(height = smallVerticalSpacer)
            }

        }

    }

}