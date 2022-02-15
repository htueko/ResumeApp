package com.htueko.resumeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * domain model for project properties
 */
@Parcelize
data class Project(
    val projectName: String = "",
    val teamSize: Int  = 0,
    val projectSummary: String = "",
    val role: String = "",
    val technology: String = ""
): Parcelable