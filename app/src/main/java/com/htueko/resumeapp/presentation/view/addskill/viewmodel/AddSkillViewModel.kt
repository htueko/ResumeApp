package com.htueko.resumeapp.presentation.view.addskill.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htueko.resumeapp.domain.model.DetailResume
import com.htueko.resumeapp.domain.model.Skill
import com.htueko.resumeapp.domain.usecase.GetResumeByIdUseCase
import com.htueko.resumeapp.domain.usecase.InsertOrUpdateSkillsUseCase
import com.htueko.resumeapp.presentation.common.commonstate.CommonUiEvent
import com.htueko.resumeapp.presentation.view.addskill.state.AddSkillUserEvent
import com.htueko.resumeapp.presentation.view.destinations.AddSkillScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSkillViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getResumeByIdUseCase: GetResumeByIdUseCase,
    private val insertOrUpdateSkillsUseCase: InsertOrUpdateSkillsUseCase
) : ViewModel() {

    // to get the navigation args from other screen.
    val navArgs = AddSkillScreenDestination.argsFrom(savedStateHandle)

    private val _uiEvent = Channel<CommonUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    // state variable to hold the skill name value
    private val _skillName = MutableStateFlow("")
    val skillName = _skillName.asStateFlow()

    // state variable to hold the skill name text field have error or not
    private val _hasSkillNameError = MutableStateFlow<Boolean>(false)
    val hasSkillNameError = _hasSkillNameError.asStateFlow()

    init {
        getResumeDetail()
    }

    // state variables to hold the domain model
    private val _resume = MutableStateFlow<DetailResume>(DetailResume())
    val resume = _resume.asStateFlow()

    // to get resume detail from database
    private fun getResumeDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getResumeByIdUseCase(navArgs.resumeId)
            response?.let {
                _resume.value = it
            }
        }
    }

    /**
     * to perform relative task when user do some ui operation.
     * @see [AddSkillUserEvent]
     */
    fun onEvent(event: AddSkillUserEvent) {
        when (event) {
            is AddSkillUserEvent.OnSkillNameChanged -> {
                onSkillNameChanged(event.skillName)
            }
            AddSkillUserEvent.OnSaveClick -> {
                onSaveButtonClick()
            }
        }
    }

    private fun basicValidationForText(value: String) = value.isBlank()

    private fun onSkillNameChanged(value: String) {
        if (basicValidationForText(value)) {
            // the skill name value is blank, so set the error to true to show error message.
            _hasSkillNameError.value = !_hasSkillNameError.value
        } else {
            // skill name value is not blank, set the value.
            _skillName.value = value
            _hasSkillNameError.value = false
        }
    }

    private fun onSaveButtonClick() {
        if (!_hasSkillNameError.value) {
            // don't have any error, check again for blank input
            if (_skillName.value.isNotBlank()) {
                val data = Skill(
                    skillName = _skillName.value
                )
                addSkill(data)
            } else {
                sendUiEvent(CommonUiEvent.ShowSnackBar)
            }
        } else {
            sendUiEvent(CommonUiEvent.ShowSnackBar)
        }
    }

    private fun addSkill(skill: Skill) {
        viewModelScope.launch {
            insertOrUpdateSkillsUseCase(navArgs.resumeId, listOf(skill))
            sendUiEvent(CommonUiEvent.PopBackStack)
        }
    }

    private fun sendUiEvent(event: CommonUiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}