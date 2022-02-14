package com.htueko.resumeapp.domain.model

/**
 * domain model for education properties
 */
data class Education(
    val schoolClass: String = "",
    val passingYear: Int = 0,
    val percentageOrCgpa: Double= 0.0
)