package com.htueko.resumeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * domain model for education properties
 */
@Parcelize
data class Education(
    val educationId: Int = 0,
    val parentId: Int = 0,
    val schoolClass: String = "",
    val passingYear: Int = 0,
    val percentageOrCgpa: Double = 0.0
) : Parcelable
