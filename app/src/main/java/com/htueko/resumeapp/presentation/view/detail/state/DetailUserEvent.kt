package com.htueko.resumeapp.presentation.view.detail.state

import com.htueko.resumeapp.domain.model.Education
import com.htueko.resumeapp.domain.model.Project
import com.htueko.resumeapp.domain.model.Resume
import com.htueko.resumeapp.domain.model.Skill
import com.htueko.resumeapp.domain.model.Work

/**
 * a model that represented everything that user can do on detail screen.
 */
sealed class DetailUserEvent {

    // to edit the educations.
    data class OnEducationsEditClick(val educations: List<Education>) : DetailUserEvent()

    // to edit the projects.
    data class OnProjectEditClick(val projects: List<Project>) : DetailUserEvent()

    // to edit the resume.
    data class OnResumeEditClick(val resume: Resume) : DetailUserEvent()

    // to edit the skills.
    data class OnSkillsEditClick(val skills: List<Skill>) : DetailUserEvent()

    // to edit the works.
    data class OnWorksEditClick(val works: List<Work>) : DetailUserEvent()

}