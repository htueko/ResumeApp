package com.htueko.resumeapp.presentation.view.main.state

import com.htueko.resumeapp.domain.model.Resume

/**
 * a model that represented everything that user can do on main screen.
 */
sealed class DashboardUserEvent {
    // to delete a resume from main screen.
    data class OnDeleteResumeClick(val resume: Resume) : DashboardUserEvent()

    // to undelete a resume from main screen.
    object OnUndoDeleteResumeClick : DashboardUserEvent()

}