package com.htueko.resumeapp.presentation.view.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htueko.resumeapp.domain.model.Resume
import com.htueko.resumeapp.domain.usecase.DeleteResumeUseCase
import com.htueko.resumeapp.domain.usecase.GetResumeUseCase
import com.htueko.resumeapp.domain.usecase.InsertOrUpdateResumeUseCase
import com.htueko.resumeapp.presentation.common.commonstate.CommonUiEvent
import com.htueko.resumeapp.presentation.view.main.state.DashboardUserEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getResumeUseCase: GetResumeUseCase,
    private val insertOrUpdateResumeUseCase: InsertOrUpdateResumeUseCase,
    private val deleteResumeUseCase: DeleteResumeUseCase,
) : ViewModel() {

    // to perform common ui operation.
    private val _uiEvent = Channel<CommonUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    // to restore the deleted resume
    private var deletedResume: Resume? = null

    // state variables to hold the domain model
    private val _resumes = MutableStateFlow<List<Resume>>(emptyList())
    val resumes = _resumes.asStateFlow()

    init {
        getResumes()
    }

    // to get all resume from database
    private fun getResumes() {
        viewModelScope.launch {
            getResumeUseCase()
                .collect { list ->
                    _resumes.value = list
                }

        }
    }

    /**
     * to perform relative task when user do some ui operation.
     * @see [DashboardUserEvent]
     */
    fun onEvent(event: DashboardUserEvent) {
        when (event) {
            is DashboardUserEvent.OnDeleteResumeClick -> {
                // hold the resume instance if user decided to restore later.
                deletedResume = event.resume
                // delete the resume that user selected
                onDeleteResumeClicked(event)
                // show the snack bar with message and action that can restore the recently deleted resume.
                sendUiEvent(CommonUiEvent.ShowSnackBar)
            }
            DashboardUserEvent.OnUndoDeleteResumeClick -> {
                // restore (insert) previously deleted resume
                onUndoDeleteResumeClicked()
            }
        }
    }

    private fun onDeleteResumeClicked(event: DashboardUserEvent.OnDeleteResumeClick) {
        viewModelScope.launch {
            deleteResumeUseCase(event.resume)
        }
    }

    private fun onUndoDeleteResumeClicked() {
        deletedResume?.let { resume ->
            viewModelScope.launch {
                insertOrUpdateResumeUseCase(resume)
            }
        }
    }

    private fun sendUiEvent(event: CommonUiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }


}