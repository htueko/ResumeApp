package com.htueko.resumeapp.presentation.common.navargs

/**
 * a model to pass the data within destination with resume id.
 * @see [https://proandroiddev.com/compose-destinations-simpler-and-safer-navigation-in-compose-with-no-compromises-74a59c6b727d]
 */
data class ResumeNavArgs(
    val resumeId: Int,
)
