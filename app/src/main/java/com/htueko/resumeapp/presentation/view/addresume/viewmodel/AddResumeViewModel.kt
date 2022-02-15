package com.htueko.resumeapp.presentation.view.addresume.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.htueko.resumeapp.domain.model.Resume
import com.htueko.resumeapp.domain.usecase.InsertOrUpdateResumeUseCase
import com.htueko.resumeapp.presentation.view.addresume.state.AddResumeUserEvent
import com.htueko.resumeapp.presentation.view.destinations.AddResumeScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddResumeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val insertOrUpdateResumeUseCase: InsertOrUpdateResumeUseCase,
): ViewModel() {

    // to get the navigation args from other screen.
    val navArgs = AddResumeScreenDestination.argsFrom(savedStateHandle)

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

    private fun isNewResume(): Boolean {
        return navArgs.resumeId == -1
    }

    // to get resume detail from database
    private fun saveResume() {
        viewModelScope.launch {
            val response = getResumeByIdUseCase(navArgs.resumeId)
            response?.let { _resume.value = it }
        }
    }

    /**
     * to perform relative task when user do some ui operation.
     * @see [AddResumeUserEvent]
     */
    fun onEvent(event: AddResumeUserEvent) {
        when (event) {
            is AddResumeUserEvent.OnAddressChanged -> TODO()
            is AddResumeUserEvent.OnCareerObjectiveChanged -> TODO()
            is AddResumeUserEvent.OnEmailAddressChanged -> TODO()
            is AddResumeUserEvent.OnImageUrlChanged -> TODO()
            is AddResumeUserEvent.OnMobileNumberChanged -> TODO()
            is AddResumeUserEvent.OnNameChanged -> TODO()
            AddResumeUserEvent.OnSaveClick -> TODO()
            is AddResumeUserEvent.OnTotalYearsOfExperienceChanged -> TODO()
        }
    }

}