package com.htueko.resumeapp.domain.model

/**
 * domain model for project properties
 */
data class Project(
    val projectName: String = "",
    val teamSize: Int  = 0,
    val projectSummary: String = "",
    val role: String = "",
    val technology: String = ""
)