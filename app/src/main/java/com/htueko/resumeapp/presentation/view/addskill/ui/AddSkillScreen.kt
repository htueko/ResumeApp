package com.htueko.resumeapp.presentation.view.addskill.ui

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
import com.htueko.resumeapp.presentation.view.addskill.state.AddSkillUserEvent
import com.htueko.resumeapp.presentation.view.addskill.viewmodel.AddSkillViewModel

@Composable
fun AddSkillScreen(
    resumeId: Int?,
    onSaveSkillClick: (Int) -> Unit,
    viewModel: AddSkillViewModel = hiltViewModel(),
) {
// to get the state of the scaffold
    val scaffoldState = rememberScaffoldState()

    // to collect the resume as state
    val data by viewModel.resume.collectAsState()
    val skills = data.skills
    val skillName by viewModel.skillName.collectAsState()
    val hasSkillNameError by viewModel.hasSkillNameError.collectAsState()

    // string to shows
    val toolbarTitle =
        data.resume.name.replaceFirstChar { it.uppercase() }
    val textSkill = stringResource(id = R.string.skill)
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
                    onSaveSkillClick(event.resumeId)
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

            // adding new skills section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(mediumPadding)
                    .verticalScroll(rememberScrollState())
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // name text field
                VerticalSpacer(height = mediumVerticalSpacer)
                TextFieldPrimary(
                    text = skillName,
                    labelText = stringResource(id = R.string.skillName),
                    onTextChanged = {
                        viewModel.onEvent(
                            AddSkillUserEvent.OnSkillNameChanged(it)
                        )
                    },
                    hasError = hasSkillNameError,
                    errorMessage = stringResource(id = R.string.error_required),
                    keyboardType = KeyboardType.Text,
                )

                // save button
                VerticalSpacer(height = mediumVerticalSpacer)
                ButtonPrimary(
                    text = textSave,
                    onClick = {
                        viewModel.onEvent(
                            AddSkillUserEvent.OnSaveClick
                        )
                    },
                )
                VerticalSpacer(height = mediumVerticalSpacer)
            }

            // existing skills column
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
                TitleText(text = textSkill)
                VerticalSpacer(height = smallVerticalSpacer)
                if (skills.isNotEmpty()) {
                    skills.forEach { skill ->
                        BodyText(text = skill.skillName)
                        VerticalSpacer(height = smallVerticalSpacer)
                    }
                }
                VerticalSpacer(height = smallVerticalSpacer)
            }
        }
    }
}
