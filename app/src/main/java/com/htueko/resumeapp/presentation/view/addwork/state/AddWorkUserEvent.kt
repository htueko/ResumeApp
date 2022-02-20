package com.htueko.resumeapp.presentation.view.addwork.state

/**
 * a model that represented everything that user can do on add work screen.
 */
sealed class AddWorkUserEvent {
    // to save the company name
    data class OnCompanyNameChanged(val companyName: String) : AddWorkUserEvent()

    // to save the duration
    data class OnDurationChanged(val duration: String) : AddWorkUserEvent()

    // to save the user input.
    object OnSaveClick : AddWorkUserEvent()
}
