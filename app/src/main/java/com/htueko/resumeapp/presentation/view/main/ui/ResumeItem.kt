package com.htueko.resumeapp.presentation.view.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.htueko.resumeapp.R
import com.htueko.resumeapp.domain.model.Resume
import com.htueko.resumeapp.presentation.theme.elevation
import com.htueko.resumeapp.presentation.theme.spacing
import com.htueko.resumeapp.presentation.view.main.state.DashboardUserEvent


@ExperimentalMaterialApi
@Composable
fun ResumeItem(
    modifier: Modifier = Modifier,
    resume: Resume,
    onResumeClick: () -> Unit,
    onDeleteResumeClick: (DashboardUserEvent.OnDeleteResumeClick) -> Unit,
) {
    val paddingModifier = MaterialTheme.spacing.extraSmall
    val elevationModifier = MaterialTheme.elevation.extraSmall
    Card(
        modifier = modifier
            .padding(paddingModifier),
        elevation = elevationModifier,
        onClick = onResumeClick
    ) {
        Column() {
            val careerObjective = resume.careerObjective
            Row() {
                Text(text = resume.name)
                IconButton(onClick = onResumeClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(id = R.string.delete)
                    )
                }
            }
            Text(text = careerObjective)
        }
    }

}
