package com.htueko.resumeapp.presentation.view.addeducation.state

/**
 * a model that represented everything that user can do on add education screen.
 */
sealed class AddEducationUserEvent {
    // to save the school name
    data class OnSchoolNameChanged(val schoolName: String) : AddEducationUserEvent()

    // to save the passing year
    data class OnPassingYearChanged(val passingYear: String) : AddEducationUserEvent()

    // to save the percentage or CGPA
    data class OnPercentageOrCgpaChanged(val percentageOrCgpa: String) : AddEducationUserEvent()

    // to save the user input.
    object OnSaveClick : AddEducationUserEvent()

}