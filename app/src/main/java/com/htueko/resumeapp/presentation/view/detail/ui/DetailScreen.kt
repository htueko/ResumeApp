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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.htueko.resumeapp.R
import com.htueko.resumeapp.presentation.common.component.BodyText
import com.htueko.resumeapp.presentation.common.component.RoundAvatarImage
import com.htueko.resumeapp.presentation.common.component.TitleText
import com.htueko.resumeapp.presentation.common.component.VerticalSpacer
import com.htueko.resumeapp.presentation.common.navargs.ResumeNavArgs
import com.htueko.resumeapp.presentation.theme.spacing
import com.htueko.resumeapp.presentation.view.detail.viewmodel.DetailViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(
    navArgsDelegate = ResumeNavArgs::class
)
@Composable
fun DetailScreen(
    navigator: DestinationsNavigator,
    viewModel: DetailViewModel = hiltViewModel()
) {
    // to get the state of the scaffold
    val scaffoldState = rememberScaffoldState()

    // to collect the resume as state
    val data = viewModel.resume.collectAsState().value
    val resume = data?.resume
    val educations = data?.educations
    val projects = data?.projects
    val skills = data?.skills
    val works = data?.works

    // string to shows
    val toolbarTitle = resume?.name ?: stringResource(id = R.string.resume)
    val textResume = stringResource(id = R.string.resume)
    val textEducation = stringResource(id = R.string.education)
    val textProject = stringResource(id = R.string.project)
    val textSkill = stringResource(id = R.string.skill)
    val textWork = stringResource(id = R.string.work)
    val textAdd = stringResource(id = R.string.add)
    val textMore = stringResource(id = R.string.more)
    val textEdit = stringResource(id = R.string.edit)

    // dimens
    val smallPadding = MaterialTheme.spacing.small
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
                .padding(smallPadding)
                .verticalScroll(rememberScrollState()),
        ) {
            // Image view column
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .padding(smallPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (resume?.avatarUrl?.isNotBlank() == true) {
                    RoundAvatarImage(
                        imageUrl = resume.avatarUrl,
                        contentDescription = resume.name
                    )
                } else {
                    RoundAvatarImage()
                }
            }
            VerticalSpacer(height = mediumVerticalSpacer)

            // Resume properties section
            TitleText(text = textResume)
            VerticalSpacer(height = smallVerticalSpacer)
            resume?.let {
                BodyText(text = it.name)
                BodyText(text = it.mobileNumber)
                BodyText(text = it.emailAddress)
                BodyText(text = it.careerObjective)
                BodyText(text = it.totalYearsOfExperience.toString())
                BodyText(text = it.address)
                VerticalSpacer(height = mediumVerticalSpacer)
            }

            // Educations properties section
            TitleText(text = textEducation)
            VerticalSpacer(height = smallVerticalSpacer)
            educations?.let { list ->
                list.forEach { education ->
                    BodyText(text = education.schoolClass)
                    BodyText(text = education.passingYear.toString())
                    BodyText(text = education.percentageOrCgpa.toString())
                    VerticalSpacer(height = smallVerticalSpacer)
                }
                VerticalSpacer(height = mediumVerticalSpacer)
            }

            // Project properties section
            TitleText(text = textProject)
            VerticalSpacer(height = smallVerticalSpacer)
            projects?.let { list ->
                list.forEach { project ->
                    BodyText(text = project.projectName)
                    BodyText(text = project.teamSize.toString())
                    BodyText(text = project.projectSummary)
                    BodyText(text = project.role)
                    BodyText(text = project.technology)
                    VerticalSpacer(height = smallVerticalSpacer)
                }
                VerticalSpacer(height = mediumVerticalSpacer)
            }

            // Skill property section
            TitleText(text = textSkill)
            VerticalSpacer(height = smallVerticalSpacer)
            skills?.let { list ->
                list.forEach { skill ->
                    BodyText(text = skill.skillName)
                    VerticalSpacer(height = smallVerticalSpacer)
                }
                VerticalSpacer(height = mediumVerticalSpacer)
            }

            // Work properties section
            TitleText(text = textWork)
            VerticalSpacer(height = smallVerticalSpacer)
            works?.let { list ->
                list.forEach { work ->
                    BodyText(text = work.companyName)
                    BodyText(text = "${work.duration} months")
                    VerticalSpacer(height = smallVerticalSpacer)
                }
                VerticalSpacer(height = mediumVerticalSpacer)
            }


        }
    }

}
