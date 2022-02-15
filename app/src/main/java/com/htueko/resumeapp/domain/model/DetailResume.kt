package com.htueko.resumeapp.domain.model

/**
 * detail model for displaying everything related to
 * [Resume], [Education], [Project], [Skill] and [Work]
 * @param [resume] a resume instance.
 * @param [educations] a list of education instance.
 * @param [projects] a list of project instance.
 * @param [skills] a list of skill instance.
 * @param [works] a list of work instance.
 */
data class DetailResume(
    val resume: Resume,
    val educations: List<Education>,
    val projects: List<Project>,
    val skills: List<Skill>,
    val works: List<Work>
)