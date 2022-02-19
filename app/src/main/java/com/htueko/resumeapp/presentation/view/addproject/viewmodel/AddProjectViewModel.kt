package com.htueko.resumeapp.presentation.view.addproject.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htueko.resumeapp.domain.model.DetailResume
import com.htueko.resumeapp.domain.model.Project
import com.htueko.resumeapp.domain.usecase.GetResumeByIdUseCase
import com.htueko.resumeapp.domain.usecase.InsertOrUpdateProjectsUseCase
import com.htueko.resumeapp.presentation.common.commonstate.CommonUiEvent
import com.htueko.resumeapp.presentation.view.addproject.state.AddProjectUserEvent
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
class AddProjectViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getResumeByIdUseCase: GetResumeByIdUseCase,
    private val insertOrUpdateProjectsUseCase: InsertOrUpdateProjectsUseCase,
) : ViewModel() {

    // to get the navigation args from other screen.
    val resumeId = savedStateHandle.get<Int>("resumeId")

    private val _uiEvent = Channel<CommonUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    // state variable to hold the project name value
    private val _projectName = MutableStateFlow("")
    val projectName = _projectName.asStateFlow()

    // state variable to hold the team size value
    private val _teamSize = MutableStateFlow("")
    val teamSize = _teamSize.asStateFlow()

    // state variable to hold the project summary value
    private val _projectSummary = MutableStateFlow("")
    val projectSummary = _projectSummary.asStateFlow()

    // state variable to hold the role value
    private val _role = MutableStateFlow("")
    val role = _role.asStateFlow()

    // state variable to hold the technology value
    private val _technology = MutableStateFlow("")
    val technology = _technology.asStateFlow()

    // state variable to hold the project name text field have error or not
    private val _hasProjectNameError = MutableStateFlow<Boolean>(false)
    val hasProjectNameError = _hasProjectNameError.asStateFlow()

    // state variable to hold the team size text field have error or not
    private val _hasTeamSizeError = MutableStateFlow<Boolean>(false)
    val hasTeamSizeError = _hasTeamSizeError.asStateFlow()

    // state variable to hold the project summary text field have error or not
    private val _hasProjectSummaryError = MutableStateFlow<Boolean>(false)
    val hasProjectSummaryError = _hasProjectSummaryError.asStateFlow()

    // state variable to hold the role text field have error or not
    private val _hasRoleError = MutableStateFlow<Boolean>(false)
    val hasRoleError = _hasRoleError.asStateFlow()

    // state variable to hold the technology text field have error or not
    private val _hasTechnologyError = MutableStateFlow<Boolean>(false)
    val hasTechnologyError = _hasTechnologyError.asStateFlow()

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
                detailResume.projects.forEach { project ->
                    if (project.parentId == resumeId) {
                        _resume.value = detailResume
                    }
                }
            }
        }
    }

    /**
     * to perform relative task when user do some ui operation.
     * @see [AddProjectUserEvent]
     */
    fun onEvent(event: AddProjectUserEvent) {
        when (event) {
            is AddProjectUserEvent.OnProjectNameChanged -> {
                onProjectNameChanged(event.projectName)
            }
            is AddProjectUserEvent.OnProjectSummaryChanged -> {
                onProjectSummaryChanged(event.projectSummary)
            }
            is AddProjectUserEvent.OnRoleChanged -> {
                onRoleChanged(event.role)
            }
            is AddProjectUserEvent.OnTeamSizeChanged -> {
                onTeamSizeChanged(event.teamSize)
            }
            is AddProjectUserEvent.OnTechnologyChanged -> {
                onTechnologyChanged(event.technology)
            }
            AddProjectUserEvent.OnSaveClick -> {
                onSaveButtonClick()
            }
        }
    }

    private fun basicValidationForText(value: String) = value.isBlank()

    private fun onProjectNameChanged(value: String) {
        if (basicValidationForText(value)) {
            // the project name value is blank, so set the error to true to show error message.
            _hasProjectNameError.value = !_hasProjectNameError.value
        } else {
            // project name value is not blank, set the value.
            _projectName.value = value
            _hasProjectNameError.value = false
        }
    }

    private fun onTeamSizeChanged(value: String) {
        if (basicValidationForText(value)) {
            // the team size value is blank, so set the error to true to show error message.
            _hasTeamSizeError.value = !_hasTeamSizeError.value
        } else {
            // team size value is not blank, set the value.
            _teamSize.value = value
            _hasTeamSizeError.value = false
        }
    }

    private fun onProjectSummaryChanged(value: String) {
        if (basicValidationForText(value)) {
            // the project summary value is blank, so set the error to true to show error message.
            _hasProjectSummaryError.value = !_hasProjectSummaryError.value
        } else {
            // project summary value is not blank, set the value.
            _projectSummary.value = value
            _hasProjectSummaryError.value = false
        }
    }

    private fun onRoleChanged(value: String) {
        if (basicValidationForText(value)) {
            // the role value is blank, so set the error to true to show error message.
            _hasRoleError.value = !_hasRoleError.value
        } else {
            // role value is not blank, set the value.
            _role.value = value
            _hasRoleError.value = false
        }
    }

    private fun onTechnologyChanged(value: String) {
        if (basicValidationForText(value)) {
            // the technology value is blank, so set the error to true to show error message.
            _hasTechnologyError.value = !_hasTechnologyError.value
        } else {
            // technology value is not blank, set the value.
            _technology.value = value
            _hasTechnologyError.value = false
        }
    }

    private fun onSaveButtonClick() {
        if (!_hasProjectNameError.value && !_hasTeamSizeError.value
            && !_hasProjectSummaryError.value && !_hasRoleError.value
            && !_hasTechnologyError.value
        ) {
            // don't have any error, check again for blank input
            if (_projectName.value.isNotBlank() && _teamSize.value.isNotBlank()
                && _projectSummary.value.isNotBlank() && _role.value.isNotBlank()
                && _technology.value.isNotBlank()
            ) {
                resumeId?.let {
                    val data = Project(
                        parentId = it,
                        projectName = _projectName.value,
                        teamSize = _teamSize.value.toInt(),
                        projectSummary = _projectSummary.value,
                        role = _role.value,
                        technology = _technology.value
                    )
                    addProject(it, data)
                }
            } else {
                sendUiEvent(CommonUiEvent.ShowSnackBar)
            }
        } else {
            sendUiEvent(CommonUiEvent.ShowSnackBar)
        }
    }

    private fun addProject(resumeId: Int, project: Project) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                // to get the last inserted project id from database.
                val data = insertOrUpdateProjectsUseCase(resumeId, listOf(project))
                // do have data (projectId) means we successfully insert the data,
                // so send back current resumeId to the detail screen and pop back this route.
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