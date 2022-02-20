package com.htueko.resumeapp.presentation.view.detail.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.htueko.resumeapp.domain.model.Education
import com.htueko.resumeapp.presentation.common.component.BodyText
import com.htueko.resumeapp.presentation.common.component.OutlinePrimaryButton
import com.htueko.resumeapp.presentation.common.component.TitleText
import com.htueko.resumeapp.presentation.common.component.VerticalSpacer

@Composable
fun EducationColumn(
    title: String,
    smallPadding: Dp,
    mediumPadding: Dp,
    educations: List<Education>,
    editText: String,
    onButtonClick: () -> Unit,
    addText: String,
    onAddButtonClick: () -> Unit,
) {
    TitleText(text = title)
    VerticalSpacer(height = smallPadding)
    if (educations.isNotEmpty()) {
        educations.forEach { education ->
            TitleRow(
                titleText = education.schoolClass,
                buttonText = editText,
                contentDescription = editText,
                onButtonClick = onButtonClick
            )
            BodyText(text = education.passingYear.toString())
            BodyText(text = education.percentageOrCgpa.toString())
            VerticalSpacer(height = smallPadding)
        }
        VerticalSpacer(height = mediumPadding)
    }
    // to add new education
    OutlinePrimaryButton(
        text = addText,
        contentDescription = addText,
        imageVector = Icons.Outlined.AddCircle,
        onClick = onAddButtonClick,
    )
    VerticalSpacer(height = mediumPadding)
}
