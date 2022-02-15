package com.htueko.resumeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * domain model for skill properties
 */
@Parcelize
data class Skill(
    val skillName: String = ""
): Parcelable