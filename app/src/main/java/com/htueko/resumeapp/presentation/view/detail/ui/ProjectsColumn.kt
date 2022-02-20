package com.htueko.resumeapp.presentation.view.detail.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.htueko.resumeapp.domain.model.Education
import com.htueko.resumeapp.domain.model.Project
import com.htueko.resumeapp.presentation.common.component.BodyText
import com.htueko.resumeapp.presentation.common.component.OutlinePrimaryButton
import com.htueko.resumeapp.presentation.common.component.TitleText
import com.htueko.resumeapp.presentation.common.component.VerticalSpacer

@Composable
fun ProjectColumn(
    title: String,
    smallPadding: Dp,
    mediumPadding: Dp,
    projects: List<Project>,
    editText: String,
    onButtonClick: () -> Unit,
    addText: String,
    onAddButtonClick: () -> Unit,
) {
    TitleText(text = title)
    VerticalSpacer(height = smallPadding)
    if (projects.isNotEmpty()) {
        projects.forEach { project ->
            TitleRow(
                titleText = project.projectName,
                buttonText = editText,
                contentDescription = editText,
                onButtonClick = onButtonClick
            )
            BodyText(text = project.teamSize.toString())
            BodyText(text = project.projectSummary)
            BodyText(text = project.role)
            BodyText(text = project.technology)
            VerticalSpacer(height = smallPadding)
        }
        VerticalSpacer(height = mediumPadding)
    }
    // to add new project
    OutlinePrimaryButton(
        text = addText,
        contentDescription = addText,
        imageVector = Icons.Outlined.AddCircle,
        onClick = onAddButtonClick,
    )
    VerticalSpacer(height = mediumPadding)
}