package com.htueko.resumeapp.presentation.view.addproject.state


/**
 * a model that represented everything that user can do on add project screen.
 */
sealed class AddProjectUserEvent {
    // to save the project name
    data class OnProjectNameChanged(val projectName: String) : AddProjectUserEvent()

    // to save the team size
    data class OnTeamSizeChanged(val teamSize: String) : AddProjectUserEvent()

    // to save the project summary
    data class OnProjectSummaryChanged(val projectSummary: String) : AddProjectUserEvent()

    // to save the role
    data class OnRoleChanged(val role: String) : AddProjectUserEvent()

    // to save the technology
    data class OnTechnologyChanged(val technology: String) : AddProjectUserEvent()

    // to save the user input.
    object OnSaveClick : AddProjectUserEvent()

}