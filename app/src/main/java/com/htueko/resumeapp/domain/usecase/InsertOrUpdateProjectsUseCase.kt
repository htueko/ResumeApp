package com.htueko.resumeapp.domain.usecase

import com.htueko.resumeapp.domain.model.Project
import com.htueko.resumeapp.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * to insert or update the list of [Project] instance.
 * @param [localRepository] repository that operated for local database.
 */
class InsertOrUpdateProjectsUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(resumeId: Int, projects: List<Project>): Int? {
        return localRepository.insertOrUpdateProjects(resumeId, projects)
    }

}