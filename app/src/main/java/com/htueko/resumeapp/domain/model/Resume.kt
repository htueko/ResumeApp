package com.htueko.resumeapp.domain.model

/**
 * domain model for resume properties
 */
data class Resume(
    val resumeId: Int,
    val name: String = "",
    val avatarUrl: String = "",
    val mobileNumber: String = "",
    val emailAddress: String = "",
    val careerObjective: String = "",
    val totalYearsOfExperience: Int = 0,
    val address: String = "",
    val educations: List<Education> = emptyList(),
    val projects: List<Project> = emptyList(),
    val skills: List<Skill> = emptyList(),
    val works: List<Work> = emptyList()
)