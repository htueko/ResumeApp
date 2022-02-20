package com.htueko.resumeapp.presentation.view.addresume.state

/**
 * a model that represented everything that user can do on add resume screen.
 */
sealed class AddResumeUserEvent {
    // to save the name
    data class OnNameChanged(val name: String) : AddResumeUserEvent()

    // to save the image url
    data class OnImageUrlChanged(val imageUrl: String) : AddResumeUserEvent()

    // to save the mobile number
    data class OnMobileNumberChanged(val mobileNumber: String) : AddResumeUserEvent()

    // to save the email address
    data class OnEmailAddressChanged(val emailAddress: String) : AddResumeUserEvent()

    // to save the career objective
    data class OnCareerObjectiveChanged(val careerObjective: String) : AddResumeUserEvent()

    // to save the total years of experience
    data class OnTotalYearsOfExperienceChanged(val totalYearsOfExperience: String) : AddResumeUserEvent()

    // to save the address
    data class OnAddressChanged(val address: String) : AddResumeUserEvent()

    // to save the user input.
    object OnSaveClick : AddResumeUserEvent()
}
