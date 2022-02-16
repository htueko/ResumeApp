package com.htueko.resumeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * domain model for work properties
 */
@Parcelize
data class Work(
    val workId: Int = 0,
    val companyName: String = "",
    val duration: Int = 0
): Parcelable