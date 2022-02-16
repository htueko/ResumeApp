package com.htueko.resumeapp.presentation.view.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.htueko.resumeapp.R
import com.htueko.resumeapp.domain.model.Resume
import com.htueko.resumeapp.presentation.common.component.BodyText
import com.htueko.resumeapp.presentation.common.component.TitleText
import com.htueko.resumeapp.presentation.theme.elevation
import com.htueko.resumeapp.presentation.theme.spacing


@ExperimentalMaterialApi
@Composable
fun ResumeItem(
    modifier: Modifier = Modifier,
    resume: Resume,
    onResumeClick: () -> Unit,
    onDeleteResumeClick: () -> Unit,
) {
    val paddingModifier = MaterialTheme.spacing.medium
    val elevationModifier = MaterialTheme.elevation.small
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingModifier),
        elevation = elevationModifier,
        onClick = onResumeClick
    ) {
        Column() {
            Row() {
                Column(modifier = Modifier.weight(5f)) {
                    TitleText(text = resume.name, modifier = Modifier.padding(elevationModifier))
                }
                Column(modifier = Modifier.weight(1f)) {
                    IconButton(onClick = onDeleteResumeClick) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(id = R.string.delete)
                        )
                    }
                }
            }
            BodyText(text = resume.mobileNumber, modifier = Modifier.padding(elevationModifier))
            BodyText(text = resume.emailAddress, modifier = Modifier.padding(elevationModifier))
            BodyText(text = resume.careerObjective, modifier = Modifier.padding(elevationModifier))
            BodyText(text = resume.address, modifier = Modifier.padding(elevationModifier))
        }
    }

}
