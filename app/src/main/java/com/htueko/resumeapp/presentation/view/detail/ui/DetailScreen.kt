package com.htueko.resumeapp.presentation.view.detail.ui

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.htueko.resumeapp.R
import com.htueko.resumeapp.presentation.common.component.RoundAvatarImage
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
    val smallPadding = MaterialTheme.spacing.small
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
            ResumeColumn(
                title = textResume,
                smallPadding = smallPadding,
                mediumPadding = mediumPadding,
                resume = resume,
                editText = textEditResume,
                onButtonClick = {
                    onEditResumeClick()
                }
            )

            // Educations properties section
            EducationColumn(
                title = textEducation,
                smallPadding = smallPadding,
                mediumPadding = mediumPadding,
                educations = educations,
                editText = textEditEducation,
                onButtonClick = { onEditEducationClick() },
                addText = textAddEducation,
                onAddButtonClick = { onAddEducationClick() },
            )

            // Project properties section
            ProjectColumn(
                title = textProject,
                smallPadding = smallPadding,
                mediumPadding = mediumPadding,
                projects = projects,
                editText = textEditProject,
                onButtonClick = { onEditProjectClick() },
                addText = textAddProject,
                onAddButtonClick = { onAddProjectClick() }
            )

            // Skill property section
            SkillsColumn(
                title = textSkill,
                smallPadding = smallPadding,
                mediumPadding = mediumPadding,
                skills = skills,
                editText = textEditSkill,
                onButtonClick = { onEditSkillClick() },
                addText = textAddSkill,
                onAddButtonClick = { onAddSkillClick() },
            )

            // Work properties section
            WorksColumn(
                title = textWork,
                smallPadding = smallPadding,
                mediumPadding = mediumPadding,
                works = works,
                editText = textEditWork,
                onButtonClick = { onEditWorkClick() },
                addText = textAddWork,
                onAddButtonClick = { onAddWorkClick() },
            )
        }
    }
}
