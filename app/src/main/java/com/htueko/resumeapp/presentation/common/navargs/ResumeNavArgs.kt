package com.htueko.resumeapp.presentation.common.navargs

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * a model to pass the data within destination with resume id.
 * @see [https://proandroiddev.com/compose-destinations-simpler-and-safer-navigation-in-compose-with-no-compromises-74a59c6b727d]
 */
@Suppress("MaxLineLength")
@Parcelize
data class ResumeNavArgs(
    val resumeId: Int,
) : Parcelable
