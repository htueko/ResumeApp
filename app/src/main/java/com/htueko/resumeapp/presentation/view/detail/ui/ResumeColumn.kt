package com.htueko.resumeapp.presentation.view.detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.htueko.resumeapp.domain.model.Resume
import com.htueko.resumeapp.presentation.common.component.BodyText
import com.htueko.resumeapp.presentation.common.component.TitleText
import com.htueko.resumeapp.presentation.common.component.VerticalSpacer

@Composable
fun ResumeColumn(
    title: String,
    smallPadding: Dp,
    mediumPadding: Dp,
    resume: Resume,
    editText: String,
    onButtonClick: () -> Unit,
) {
    TitleText(text = title)
    VerticalSpacer(height = smallPadding)
    resume.let {
        TitleRow(
            titleText = it.name,
            buttonText = editText,
            contentDescription = editText,
            onButtonClick = onButtonClick
        )
        BodyText(text = it.mobileNumber)
        BodyText(text = it.emailAddress)
        BodyText(text = it.careerObjective)
        BodyText(text = it.totalYearsOfExperience.toString() + " years")
        BodyText(text = it.address)
        VerticalSpacer(height = mediumPadding)
    }
}