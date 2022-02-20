package com.htueko.resumeapp.presentation.view.detail.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.htueko.resumeapp.domain.model.Skill
import com.htueko.resumeapp.presentation.common.component.OutlinePrimaryButton
import com.htueko.resumeapp.presentation.common.component.TitleText
import com.htueko.resumeapp.presentation.common.component.VerticalSpacer

@Composable
fun SkillsColumn(
    title: String,
    smallPadding: Dp,
    mediumPadding: Dp,
    skills: List<Skill>,
    editText: String,
    onButtonClick: () -> Unit,
    addText: String,
    onAddButtonClick: () -> Unit,
) {
    TitleText(text = title)
    VerticalSpacer(height = smallPadding)
    if (skills.isNotEmpty()) {
        skills.forEach { skill ->
            TitleRow(
                titleText = skill.skillName,
                buttonText = editText,
                contentDescription = editText,
                onButtonClick = onButtonClick
            )
            VerticalSpacer(height = smallPadding)
        }
        VerticalSpacer(height = mediumPadding)
    }
    // to add new skill
    OutlinePrimaryButton(
        text = addText,
        contentDescription = addText,
        imageVector = Icons.Outlined.AddCircle,
        onClick = onAddButtonClick,
    )
    VerticalSpacer(height = mediumPadding)
}
