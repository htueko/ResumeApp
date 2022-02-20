package com.htueko.resumeapp.presentation.view.addskill.state

/**
 * a model that represented everything that user can do on add skill screen.
 */
sealed class AddSkillUserEvent {
    // to save the skill name
    data class OnSkillNameChanged(val skillName: String) : AddSkillUserEvent()

    // to save the user input.
    object OnSaveClick : AddSkillUserEvent()
}
