package com.htueko.resumeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * domain model for work properties
 */
@Parcelize
data class Work(
    val companyName: String = "",
    val duration: Int = 0
): Parcelable