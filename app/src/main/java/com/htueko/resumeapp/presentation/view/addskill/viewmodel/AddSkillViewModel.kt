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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddSkillViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getResumeByIdUseCase: GetResumeByIdUseCase,
    private val insertOrUpdateSkillsUseCase: InsertOrUpdateSkillsUseCase
) : ViewModel() {

    // to get the navigation args from other screen.
    val resumeId = savedStateHandle.get<Int>("resumeId")

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
            val response = resumeId?.let { getResumeByIdUseCase(it) }
            response?.let { detailResume ->
                println(Thread.currentThread().name)
                detailResume.skills.forEach { skill ->
                    if (skill.parentId == resumeId) {
                        _resume.value = detailResume
                    }
                }
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
            else -> {
                // nothing to do here
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
                resumeId?.let {
                    val data = Skill(
                        parentId = it,
                        skillName = _skillName.value
                    )
                    addSkill(it, data)
                }
            } else {
                sendUiEvent(CommonUiEvent.ShowSnackBar)
            }
        } else {
            sendUiEvent(CommonUiEvent.ShowSnackBar)
        }
    }

    private fun addSkill(resumeId: Int, skill: Skill) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                println(resume.value.resume.resumeId)
                val data = insertOrUpdateSkillsUseCase(resumeId, listOf(skill))
                data?.let {
                    sendUiEvent(CommonUiEvent.PopBackStackAndSendData(resumeId))
                }
            }
        }
    }

    private fun sendUiEvent(event: CommonUiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}