package com.htueko.resumeapp.presentation.view.detail.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.htueko.resumeapp.domain.model.Work
import com.htueko.resumeapp.presentation.common.component.BodyText
import com.htueko.resumeapp.presentation.common.component.OutlinePrimaryButton
import com.htueko.resumeapp.presentation.common.component.TitleText
import com.htueko.resumeapp.presentation.common.component.VerticalSpacer

@Composable
fun WorksColumn(
    title: String,
    smallPadding: Dp,
    mediumPadding: Dp,
    works: List<Work>,
    editText: String,
    onButtonClick: () -> Unit,
    addText: String,
    onAddButtonClick: () -> Unit,
) {
    TitleText(text = title)
    VerticalSpacer(height = smallPadding)
    if (works.isNotEmpty()) {
        works.forEach { work ->
            TitleRow(
                titleText = work.companyName,
                buttonText = editText,
                contentDescription = editText,
                onButtonClick = onButtonClick
            )
            BodyText(text = "${work.duration} months")
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
