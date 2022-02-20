package com.htueko.resumeapp.presentation.view.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.htueko.resumeapp.R
import com.htueko.resumeapp.presentation.common.component.BodyText
import com.htueko.resumeapp.presentation.common.component.ButtonPrimary
import com.htueko.resumeapp.presentation.common.component.OutlinePrimaryButton
import com.htueko.resumeapp.presentation.common.component.RoundAvatarImage
import com.htueko.resumeapp.presentation.common.component.TitleText
import com.htueko.resumeapp.presentation.common.component.VerticalSpacer
import com.htueko.resumeapp.presentation.theme.spacing
import com.htueko.resumeapp.presentation.view.detail.viewmodel.DetailViewModel


@Composable
fun DetailScreen(
    onEditResumeClick: () -> Unit,
    onEditEducationClick: () -> Unit,
    onAddEducationClick: () -> Unit,
    onEditProjectClick: () -> Unit,
    onAddProjectClick: () -> Unit,
    onEditSkillClick: () -> Unit,
    onAddSkillClick: () -> Unit,
    onEditWorkClick: () -> Unit,
    onAddWorkClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    // to get the state of the scaffold
    val scaffoldState = rememberScaffoldState()

    // to collect the resume as state
    val data by viewModel.resume.collectAsState()
    val resume = data.resume
    val educations = data.educations
    val projects = data.projects
    val skills = data.skills
    val works = data.works

    // string to shows
    val toolbarTitle =
        resume.name.replaceFirstChar { it.uppercase() }
    val textResume = stringResource(id = R.string.resume)
    val textEducation = stringResource(id = R.string.education)
    val textProject = stringResource(id = R.string.project)
    val textSkill = stringResource(id = R.string.skill)
    val textWork = stringResource(id = R.string.work)
    val textEditResume = stringResource(id = R.string.editResume)
    val textAddEducation = stringResource(id = R.string.addEducation)
    val textEditEducation = stringResource(id = R.string.editEducation)
    val textAddProject = stringResource(id = R.string.addProject)
    val textEditProject = stringResource(id = R.string.editProject)
    val textAddSkill = stringResource(id = R.string.addSkill)
    val textEditSkill = stringResource(id = R.string.editSkill)
    val textAddWork = stringResource(id = R.string.addWork)
    val textEditWork = stringResource(id = R.string.editWork)

    // dimens
    val mediumPadding = MaterialTheme.spacing.medium
    val imageHeight = 240.dp
    val smallVerticalSpacer = MaterialTheme.spacing.small
    val mediumVerticalSpacer = MaterialTheme.spacing.medium


    // detail main screen
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = { Text(text = toolbarTitle) })
        }
    ) {
        // scrollable column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(mediumPadding)
                .verticalScroll(rememberScrollState()),
        ) {
            // Image view column
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .padding(mediumPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (resume.avatarUrl.isNotBlank()) {
                    RoundAvatarImage(
                        imageUrl = resume.avatarUrl,
                        contentDescription = resume.name
                    )
                } else {
                    RoundAvatarImage(
                        drawableResId = R.drawable.ic_launcher_foreground
                    )
                }
            }
            VerticalSpacer(height = mediumVerticalSpacer)

            // Resume properties section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                TitleText(
                    text = textResume,
                    modifier = Modifier.alignByBaseline()
                )
                OutlinePrimaryButton(
                    modifier = Modifier.alignByBaseline(),
                    text = textEditResume,
                    contentDescription = textEditResume,
                    imageVector = Icons.Outlined.Edit,
                    onClick = {
                        // to navigate the add resume screen with resume id,
                        onEditResumeClick()
                    }
                )
            }
            VerticalSpacer(height = smallVerticalSpacer)
            resume.let {
                BodyText(text = it.name)
                BodyText(text = it.mobileNumber)
                BodyText(text = it.emailAddress)
                BodyText(text = it.careerObjective)
                BodyText(text = it.totalYearsOfExperience.toString() + " years")
                BodyText(text = it.address)
                VerticalSpacer(height = mediumVerticalSpacer)
            }

            // Educations properties section
            TitleText(text = textEducation)
            VerticalSpacer(height = smallVerticalSpacer)
            if (educations.isNotEmpty()) {
                educations.forEach { education ->
                    BodyText(text = education.schoolClass)
                    BodyText(text = education.passingYear.toString())
                    BodyText(text = education.percentageOrCgpa.toString())
                    VerticalSpacer(height = smallVerticalSpacer)
                }
                VerticalSpacer(height = smallVerticalSpacer)
                // to edit the education
                ButtonPrimary(
                    text = textEditEducation,
                    onClick = {
                        // to navigate the add education screen with resume id,
                        // because this is the existing resume, not new one.
                        onEditEducationClick()
                    },
                )
                VerticalSpacer(height = mediumVerticalSpacer)
            }
            // to add new education
            ButtonPrimary(
                text = textAddEducation,
                onClick = {
                    // to navigate the add education screen with resume id,
                    // because this is the existing resume, not new one.
                    onAddEducationClick()
                },
            )
            VerticalSpacer(height = mediumVerticalSpacer)

            // Project properties section
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
                VerticalSpacer(height = smallVerticalSpacer)
                // to edit the education
                ButtonPrimary(
                    text = textEditProject,
                    onClick = {
                        // to navigate the add project screen with resume id,
                        // because this is the existing resume, not new one.
                        onEditProjectClick()
                    },
                )
                VerticalSpacer(height = mediumVerticalSpacer)
            }
            // to add new project
            ButtonPrimary(
                text = textAddProject,
                onClick = {
                    // to navigate the add project screen with resume id,
                    // because this is the existing resume, not new one.
                    onAddProjectClick()
                },
            )
            VerticalSpacer(height = mediumVerticalSpacer)

            // Skill property section
            TitleText(text = textSkill)
            VerticalSpacer(height = smallVerticalSpacer)
            if (skills.isNotEmpty()) {
                skills.forEach { skill ->
                    BodyText(text = skill.skillName)
                    VerticalSpacer(height = smallVerticalSpacer)
                }
                VerticalSpacer(height = smallVerticalSpacer)
                // to edit the skill
                ButtonPrimary(
                    text = textEditSkill,
                    onClick = {
                        // to navigate the add skill screen with resume id,
                        // because this is the existing resume, not new one.
                        onEditSkillClick()
                    },
                )
                VerticalSpacer(height = mediumVerticalSpacer)
            }
            // to add new skill
            ButtonPrimary(
                text = textAddSkill,
                onClick = {
                    // to navigate the add skill screen with resume id,
                    // because this is the existing resume, not new one.
                    onAddSkillClick()
                },
            )
            VerticalSpacer(height = mediumVerticalSpacer)

            // Work properties section
            TitleText(text = textWork)
            VerticalSpacer(height = smallVerticalSpacer)
            if (works.isNotEmpty()) {
                works.forEach { work ->
                    BodyText(text = work.companyName)
                    BodyText(text = "${work.duration} months")
                    VerticalSpacer(height = smallVerticalSpacer)
                }
                VerticalSpacer(height = smallVerticalSpacer)
                // to edit the skill
                ButtonPrimary(
                    text = textEditWork,
                    onClick = {
                        // to navigate the add work screen with resume id,
                        // because this is the existing resume, not new one.
                        onEditWorkClick()
                    },
                )
                VerticalSpacer(height = mediumVerticalSpacer)
            }
            // to add new work
            ButtonPrimary(
                text = textAddWork,
                onClick = {
                    // to navigate the add work screen with resume id,
                    // because this is the existing resume, not new one.
                    onAddWorkClick()
                },
            )
            VerticalSpacer(height = mediumVerticalSpacer)

        }
    }

}
