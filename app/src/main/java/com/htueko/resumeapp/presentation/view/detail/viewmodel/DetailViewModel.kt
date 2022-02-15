package com.htueko.resumeapp.presentation.view.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.htueko.resumeapp.domain.usecase.GetResumeByIdUseCase
import com.htueko.resumeapp.presentation.view.destinations.DetailScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getResumeByIdUseCase: GetResumeByIdUseCase
): ViewModel() {

    val resumeId = DetailScreenDestination.argsFrom(savedStateHandle)

    fun some(){

    }

}