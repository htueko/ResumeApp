package com.htueko.resumeapp.presentation.view.addwork.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htueko.resumeapp.domain.model.DetailResume
import com.htueko.resumeapp.domain.model.Work
import com.htueko.resumeapp.domain.usecase.GetResumeByIdUseCase
import com.htueko.resumeapp.domain.usecase.InsertOrUpdateWorksUseCase
import com.htueko.resumeapp.presentation.common.commonstate.CommonUiEvent
import com.htueko.resumeapp.presentation.view.addwork.state.AddWorkUserEvent
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
class AddWorkViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getResumeByIdUseCase: GetResumeByIdUseCase,
    private val insertOrUpdateWorksUseCase: InsertOrUpdateWorksUseCase
) : ViewModel() {

    // to get the navigation args from other screen.
    val resumeId = savedStateHandle.get<Int>("resumeId")

    private val _uiEvent = Channel<CommonUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    // state variable to hold the company name value
    private val _companyName = MutableStateFlow("")
    val companyName = _companyName.asStateFlow()

    // state variable to hold the duration value
    private val _duration = MutableStateFlow("")
    val duration = _duration.asStateFlow()

    // state variable to hold the company name text field have error or not
    private val _hasCompanyNameError = MutableStateFlow<Boolean>(false)
    val hasCompanyNameError = _hasCompanyNameError.asStateFlow()

    // state variable to hold the duration text field have error or not
    private val _hasDurationError = MutableStateFlow<Boolean>(false)
    val hasDurationError = _hasDurationError.asStateFlow()

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
                detailResume.works.forEach { work ->
                    if (work.parentId == resumeId) {
                        _resume.value = detailResume
                    }
                }
            }
        }
    }

    /**
     * to perform relative task when user do some ui operation.
     * @see [AddWorkUserEvent]
     */
    fun onEvent(event: AddWorkUserEvent) {
        when (event) {
            is AddWorkUserEvent.OnCompanyNameChanged -> {
                onCompanyNameChanged(event.companyName)
            }
            is AddWorkUserEvent.OnDurationChanged -> {
                onDurationChanged(event.duration)
            }
            AddWorkUserEvent.OnSaveClick -> {
                onSaveButtonClick()
            }
        }
    }

    private fun basicValidationForText(value: String) = value.isBlank()

    private fun onCompanyNameChanged(value: String) {
        if (basicValidationForText(value)) {
            // the company name value is blank, so set the error to true to show error message.
            _hasCompanyNameError.value = !_hasCompanyNameError.value
        } else {
            // company name value is not blank, set the value.
            _companyName.value = value
            _hasCompanyNameError.value = false
        }
    }

    private fun onDurationChanged(value: String) {
        if (basicValidationForText(value)) {
            // the duration value is blank, so set the error to true to show error message.
            _hasDurationError.value = !_hasDurationError.value
        } else {
            // duration value is not blank, set the value.
            _duration.value = value
            _hasDurationError.value = false
        }
    }

    private fun onSaveButtonClick() {
        if (!_hasCompanyNameError.value && !_hasDurationError.value) {
            // don't have any error, check again for blank input
            if (_companyName.value.isNotBlank() && _duration.value.isNotBlank()) {
                resumeId?.let {
                    val data = Work(
                        parentId = it,
                        companyName = _companyName.value,
                        duration = _duration.value.toInt()
                    )
                    addWork(it, data)
                }
            } else {
                sendUiEvent(CommonUiEvent.ShowSnackBar)
            }
        } else {
            sendUiEvent(CommonUiEvent.ShowSnackBar)
        }
    }

    private fun addWork(resumeId: Int, work: Work) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                // to get the last inserted work id from database.
                val data = insertOrUpdateWorksUseCase(resumeId, listOf(work))
                // do have data (workId) means we successfully insert the data,
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
