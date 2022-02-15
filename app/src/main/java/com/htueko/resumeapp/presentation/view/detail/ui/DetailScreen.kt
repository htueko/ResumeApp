package com.htueko.resumeapp.presentation.view.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.htueko.resumeapp.R
import com.htueko.resumeapp.domain.model.Resume
import com.htueko.resumeapp.presentation.common.component.RoundAvatarImage
import com.htueko.resumeapp.presentation.theme.spacing
import com.htueko.resumeapp.presentation.view.destinations.AddResumeScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DetailScreen(
    navigator: DestinationsNavigator,
    resume: Resume,
) {
    // to get the state of the scaffold
    val scaffoldState = rememberScaffoldState()

    // string to shows
    val textEdit = stringResource(id = R.string.edit)

    // dimens
    val smallPadding = MaterialTheme.spacing.small
    val imageHeight = 240.dp

    // main screen
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // navigate to add resume screen
                navigator.navigate(AddResumeScreenDestination())
            }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = textEdit
                )
            }
        },
        topBar = {
            TopAppBar(title = { Text(text = resume.name) })
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
                RoundAvatarImage(
                    imageUrl = viewModel.imageUrl.toString(),
                )
            }
        }
    }

}