package com.htueko.resumeapp.data.local.entity.relationship

import com.htueko.resumeapp.data.local.entity.ResumeEntity

/**
 * placeholder for everything related to resume as detail.
 */
data class ResumeDetail(
    val resume: ResumeEntity,
    val educations: ResumeWithEducations,
    val projects: ResumeWithProjects,
    val skills: ResumeWithSkills,
    val works: ResumeWithWorks,
)