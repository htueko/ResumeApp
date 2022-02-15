package com.htueko.resumeapp.domain.usecase

import com.htueko.resumeapp.domain.model.DetailResume
import com.htueko.resumeapp.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * to get the everything related to resume.
 * @param [localRepository] repository that operated for local database.
 * @see [DetailResume] a model to represented every field.
 */
class GetResumeByIdUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    operator fun invoke(resumeId: Int): DetailResume? {
        val resume = localRepository.getResumeById(resumeId)
        return if (resume != null) {
            val educations = localRepository.getEducations()
            val projects = localRepository.getProjects()
            val skills = localRepository.getSkills()
            val works = localRepository.getWorks()
            DetailResume(
                resume = resume,
                educations = educations,
                projects = projects,
                skills = skills,
                works = works
            )
        } else {
            null
        }


    }

}