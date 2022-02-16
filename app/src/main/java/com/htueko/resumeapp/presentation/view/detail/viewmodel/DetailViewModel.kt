package com.htueko.resumeapp.presentation.view.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htueko.resumeapp.domain.model.DetailResume
import com.htueko.resumeapp.domain.usecase.GetResumeByIdUseCase
import com.htueko.resumeapp.presentation.view.destinations.DetailScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getResumeByIdUseCase: GetResumeByIdUseCase
) : ViewModel() {

    // to get the navigation args from other screen.
    val navArgs = DetailScreenDestination.argsFrom(savedStateHandle)

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

}