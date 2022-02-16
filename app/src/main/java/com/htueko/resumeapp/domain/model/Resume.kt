package com.htueko.resumeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * domain model for resume properties
 */
@Parcelize
data class Resume(
    var resumeId: Int = -1,
    val name: String = "",
    val avatarUrl: String = "",
    val mobileNumber: String = "",
    val emailAddress: String = "",
    val careerObjective: String = "",
    val totalYearsOfExperience: Int = 0,
    val address: String = "",
): Parcelable