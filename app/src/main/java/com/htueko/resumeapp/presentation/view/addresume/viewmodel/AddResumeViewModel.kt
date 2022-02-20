package com.htueko.resumeapp.presentation.view.addresume.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htueko.resumeapp.domain.model.DetailResume
import com.htueko.resumeapp.domain.model.Resume
import com.htueko.resumeapp.domain.usecase.GetResumeByIdUseCase
import com.htueko.resumeapp.domain.usecase.InsertOrUpdateResumeUseCase
import com.htueko.resumeapp.presentation.common.commonstate.CommonUiEvent
import com.htueko.resumeapp.presentation.view.addresume.state.AddResumeUserEvent
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
class AddResumeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getResumeByIdUseCase: GetResumeByIdUseCase,
    private val insertOrUpdateResumeUseCase: InsertOrUpdateResumeUseCase,
) : ViewModel() {

    // to get the navigation args from other screen.
    val resumeId = savedStateHandle.get<Int>("resumeId")

    // state variables to hold the domain model
    private val _resume = MutableStateFlow<Resume?>(null)
    val resume = _resume.asStateFlow()

    // state variable to hold the name text field have error or not
    private val _hasNameError = MutableStateFlow<Boolean>(false)
    val hasNameError = _hasNameError.asStateFlow()

    // state variable to hold the mobile number text field have error or not
    private val _hasMobileNumberError = MutableStateFlow<Boolean>(false)
    val hasMobileNumberError = _hasMobileNumberError.asStateFlow()

    // state variable to hold the email address text field have error or not
    private val _hasEmailAddressError = MutableStateFlow<Boolean>(false)
    val hasEmailAddressError = _hasEmailAddressError.asStateFlow()

    // state variable to hold the career objective text field have error or not
    private val _hasCareerObjectiveError = MutableStateFlow<Boolean>(false)
    val hasCareerObjectiveError = _hasCareerObjectiveError.asStateFlow()

    // state variable to hold the total year of experience text field have error or not
    private val _hasTotalYearsOfExperienceError = MutableStateFlow<Boolean>(false)
    val hasTotalYearsOfExperienceError = _hasTotalYearsOfExperienceError.asStateFlow()

    // state variable to hold the address text field have error or not
    private val _hasAddressError = MutableStateFlow<Boolean>(false)
    val hasAddressError = _hasAddressError.asStateFlow()

    // state variable to hold the save button, to enable or disable
    private val _hasError = MutableStateFlow<Boolean>(true)
    val hasError = _hasError.asStateFlow()

    // state variable to hold the name value
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    // state variable to hold the image url value
    private val _avatarUrl = MutableStateFlow("")
    val avatarUrl = _avatarUrl.asStateFlow()

    // state variable to hold the mobile number value
    private val _mobileNumber = MutableStateFlow("")
    val mobileNumber = _mobileNumber.asStateFlow()

    // state variable to hold the email address value
    private val _emailAddress = MutableStateFlow("")
    val emailAddress = _emailAddress.asStateFlow()

    // state variable to hold the career objective value
    private val _careerObjective = MutableStateFlow("")
    val careerObjective = _careerObjective.asStateFlow()

    // state variable to hold the total year of experience value
    private val _totalYearsOfExperience = MutableStateFlow("")
    val totalYearsOfExperience = _totalYearsOfExperience.asStateFlow()

    // state variable to hold the address value
    private val _address = MutableStateFlow("")
    val address = _address.asStateFlow()

    init {
        getResume()
    }

    // to get resume detail from database
    private fun getResume() {
        viewModelScope.launch() {
            var response: DetailResume? = null
            withContext(Dispatchers.IO) {
                response = resumeId?.let { getResumeByIdUseCase(it) }
            }
            response?.let {
                _name.value = it.resume.name
                _avatarUrl.value = it.resume.avatarUrl
                _mobileNumber.value = it.resume.mobileNumber
                _emailAddress.value = it.resume.emailAddress
                _careerObjective.value = it.resume.careerObjective
                _totalYearsOfExperience.value = it.resume.totalYearsOfExperience.toString()
                _address.value = it.resume.address
            }
        }
    }

    private val _uiEvent = Channel<CommonUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    /**
     * to perform relative task when user do some ui operation.
     * @see [AddResumeUserEvent]
     */
    fun onEvent(event: AddResumeUserEvent) {
        when (event) {
            is AddResumeUserEvent.OnNameChanged -> {
                onNameChanged(event.name)
            }
            is AddResumeUserEvent.OnImageUrlChanged -> {
                onAvatarUrlChanged(event.imageUrl)
            }
            is AddResumeUserEvent.OnMobileNumberChanged -> {
                onMobileNumberChanged(event.mobileNumber)
            }
            is AddResumeUserEvent.OnEmailAddressChanged -> {
                onEmailAddressChanged(event.emailAddress)
            }
            is AddResumeUserEvent.OnCareerObjectiveChanged -> {
                onCareerObjectiveChanged(event.careerObjective)
            }
            is AddResumeUserEvent.OnTotalYearsOfExperienceChanged -> {
                onTotalYearsOfExperienceChanged(event.totalYearsOfExperience)
            }
            is AddResumeUserEvent.OnAddressChanged -> {
                onAddressChanged(event.address)
            }
            AddResumeUserEvent.OnSaveClick -> {
                onSaveButtonClick()
            }
        }
    }

    private fun basicValidationForText(value: String) = value.isBlank()
    private fun basicValidationForEmail(value: String): Boolean {
        return !value.contains("@")
    }

    private fun onNameChanged(value: String) {
        if (basicValidationForText(value)) {
            // the name value is blank, so set the error to true to show error message.
            _hasNameError.value = !_hasNameError.value
        } else {
            // name value is not blank, set the value.
            _name.value = value
            _hasNameError.value = false
        }
    }

    private fun onMobileNumberChanged(value: String) {
        if (basicValidationForText(value)) {
            // the mobile number value is blank, so set the error to true to show error message.
            _hasMobileNumberError.value = !_hasMobileNumberError.value
        } else {
            // mobile number value is not blank, set the value.
            _mobileNumber.value = value
            _hasMobileNumberError.value = false
        }
    }

    private fun onAvatarUrlChanged(value: String) {
        _avatarUrl.value = value
    }

    private fun onEmailAddressChanged(value: String) {
//        if (basicValidationForEmail(value)) {
//            // the email value is blank, so set the error to true to show error message.
//            _hasEmailAddressError.value = !_hasEmailAddressError.value
//        } else {
//            // email value is not blank, set the value.
//            _emailAddress.value = value
//            _hasEmailAddressError.value = false
//        }
        // fixme, setup a better way to validate email address with android sdk in viewmodel.
        _emailAddress.value = value
    }

    private fun onCareerObjectiveChanged(value: String) {
        if (basicValidationForText(value)) {
            // the career value is blank, so set the error to true to show error message.
            _hasCareerObjectiveError.value = !_hasCareerObjectiveError.value
        } else {
            // career value is not blank, set the value.
            _careerObjective.value = value
            _hasCareerObjectiveError.value = false
        }
    }

    private fun onTotalYearsOfExperienceChanged(value: String) {
        if (basicValidationForText(value)) {
            // the experience value is blank, so set the error to true to show error message.
            _hasTotalYearsOfExperienceError.value = !_hasTotalYearsOfExperienceError.value
        } else {
            // experience value is not blank, set the value.
            _totalYearsOfExperience.value = value
            _hasTotalYearsOfExperienceError.value = false
        }
    }

    private fun onAddressChanged(value: String) {
        if (basicValidationForText(value)) {
            // the address value is blank, so set the error to true to show error message.
            _hasAddressError.value = !_hasAddressError.value
        } else {
            // address value is not blank, set the value.
            _address.value = value
            _hasAddressError.value = false
        }
    }

    private fun isSaveButtonEnable() {
        _hasError.value = !(
            !hasNameError.value || !hasMobileNumberError.value ||
                !hasEmailAddressError.value || !hasCareerObjectiveError.value ||
                !hasTotalYearsOfExperienceError.value || !hasAddressError.value
            )
    }

    private fun hasAnyBlankInput(): Boolean {
        return (
            _name.value.isBlank() || _mobileNumber.value.isBlank() ||
                _emailAddress.value.isBlank() || _careerObjective.value.isBlank() ||
                _totalYearsOfExperience.value.isBlank() || _address.value.isBlank()
            )
    }

    private fun onSaveButtonClick() {
        isSaveButtonEnable()
        if (!_hasError.value) {
            // don't have any error, check again for blank input
            if (!hasAnyBlankInput()) {
                val data = Resume(
                    name = _name.value,
                    avatarUrl = _avatarUrl.value,
                    mobileNumber = _mobileNumber.value,
                    emailAddress = _emailAddress.value,
                    careerObjective = _careerObjective.value,
                    totalYearsOfExperience = _totalYearsOfExperience.value.toInt(),
                    address = _address.value
                )
                addResume(data)
            } else {
                sendUiEvent(CommonUiEvent.ShowSnackBar)
            }
        } else {
            sendUiEvent(CommonUiEvent.ShowSnackBar)
        }
    }

    private fun addResume(resume: Resume) {
        viewModelScope.launch {
            var returnData: Int? = null
            withContext(Dispatchers.IO) {
                returnData = insertOrUpdateResumeUseCase(resume)
            }
            returnData?.let { resumeId ->
                sendUiEvent(CommonUiEvent.PopBackStackAndSendData(resumeId))
            }
        }
    }

    private fun sendUiEvent(event: CommonUiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
