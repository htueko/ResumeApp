package com.htueko.resumeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * detail model for displaying everything related to
 * [Resume], [Education], [Project], [Skill] and [Work]
 * @param [resume] a resume instance.
 * @param [educations] a list of education instance.
 * @param [projects] a list of project instance.
 * @param [skills] a list of skill instance.
 * @param [works] a list of work instance.
 */
@Parcelize
data class DetailResume(
    val resume: Resume = Resume(),
    val educations: List<Education> = emptyList(),
    val projects: List<Project> = emptyList(),
    val skills: List<Skill> = emptyList(),
    val works: List<Work> = emptyList()
) : Parcelable
