package com.htueko.resumeapp.presentation.view.addeducation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htueko.resumeapp.domain.model.DetailResume
import com.htueko.resumeapp.domain.model.Education
import com.htueko.resumeapp.domain.usecase.GetResumeByIdUseCase
import com.htueko.resumeapp.domain.usecase.InsertOrUpdateEducationsUseCase
import com.htueko.resumeapp.presentation.common.commonstate.CommonUiEvent
import com.htueko.resumeapp.presentation.view.addeducation.state.AddEducationUserEvent
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
class AddEducationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getResumeByIdUseCase: GetResumeByIdUseCase,
    private val insertOrUpdateEducationsUseCase: InsertOrUpdateEducationsUseCase,
) : ViewModel() {

    // to get the navigation args from other screen.
    val resumeId = savedStateHandle.get<Int>("resumeId")

    private val _uiEvent = Channel<CommonUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    // state variable to hold the school name value
    private val _schoolName = MutableStateFlow("")
    val schoolName = _schoolName.asStateFlow()

    // state variable to hold the passing year value
    private val _passingYear = MutableStateFlow("")
    val passingYear = _passingYear.asStateFlow()

    // state variable to hold the percentage or cgpa value
    private val _percentageOrCgpa = MutableStateFlow("")
    val percentageOrCgpa = _percentageOrCgpa.asStateFlow()

    // state variable to hold the school name text field have error or not
    private val _hasSchoolNameError = MutableStateFlow<Boolean>(false)
    val hasSchoolNameError = _hasSchoolNameError.asStateFlow()

    // state variable to hold the passing year text field have error or not
    private val _hasPassingYearError = MutableStateFlow<Boolean>(false)
    val hasPassingYearError = _hasPassingYearError.asStateFlow()

    // state variable to hold the percentage or cgpa text field have error or not
    private val _hasPercentageOrCgpaError = MutableStateFlow<Boolean>(false)
    val hasPercentageOrCgpaError = _hasPercentageOrCgpaError.asStateFlow()

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
                detailResume.educations.forEach { education ->
                    if (education.parentId == resumeId) {
                        _resume.value = detailResume
                    }
                }
            }
        }
    }

    /**
     * to perform relative task when user do some ui operation.
     * @see [AddEducationUserEvent]
     */
    fun onEvent(event: AddEducationUserEvent) {
        when (event) {
            is AddEducationUserEvent.OnPassingYearChanged -> {
                onPassingYear(event.passingYear)
            }
            is AddEducationUserEvent.OnPercentageOrCgpaChanged -> {
                onPercentageOrCgpaChanged(event.percentageOrCgpa)
            }
            is AddEducationUserEvent.OnSchoolNameChanged -> {
                onSchoolNameChanged(event.schoolName)
            }
            AddEducationUserEvent.OnSaveClick -> {
                onSaveButtonClick()
            }
        }
    }

    private fun basicValidationForText(value: String) = value.isBlank()

    private fun onSchoolNameChanged(value: String) {
        if (basicValidationForText(value)) {
            // the school name value is blank, so set the error to true to show error message.
            _hasSchoolNameError.value = !_hasSchoolNameError.value
        } else {
            // school name value is not blank, set the value.
            _schoolName.value = value
            _hasSchoolNameError.value = false
        }
    }

    private fun onPassingYear(value: String) {
        if (basicValidationForText(value)) {
            // the passing year value is blank, so set the error to true to show error message.
            _hasPassingYearError.value = !_hasPassingYearError.value
        } else {
            // passing year value is not blank, set the value.
            _passingYear.value = value
            _hasPassingYearError.value = false
        }
    }

    private fun onPercentageOrCgpaChanged(value: String) {
        if (basicValidationForText(value)) {
            // the percentage value is blank, so set the error to true to show error message.
            _hasPercentageOrCgpaError.value = !_hasPercentageOrCgpaError.value
        } else {
            // percentage value is not blank, set the value.
            _percentageOrCgpa.value = value
            _hasPercentageOrCgpaError.value = false
        }
    }

    private fun onSaveButtonClick() {
        if (!_hasSchoolNameError.value && !_hasPassingYearError.value &&
            !_hasPercentageOrCgpaError.value
        ) {
            // don't have any error, check again for blank input
            if (_schoolName.value.isNotBlank() && _passingYear.value.isNotBlank() &&
                _percentageOrCgpa.value.isNotBlank()
            ) {
                resumeId?.let {
                    val data = Education(
                        parentId = it,
                        schoolClass = _schoolName.value,
                        passingYear = _passingYear.value.toInt(),
                        percentageOrCgpa = _percentageOrCgpa.value.toDouble(),
                    )
                    addEducation(it, data)
                }
            } else {
                sendUiEvent(CommonUiEvent.ShowSnackBar)
            }
        } else {
            sendUiEvent(CommonUiEvent.ShowSnackBar)
        }
    }

    private fun addEducation(resumeId: Int, education: Education) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                // to get the last inserted project id from database.
                val data = insertOrUpdateEducationsUseCase(resumeId, listOf(education))
                // do have data (educationId) means we successfully insert the data,
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
