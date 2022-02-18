package com.htueko.resumeapp.domain.usecase

import com.htueko.resumeapp.domain.model.Project
import com.htueko.resumeapp.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * to get the list of [Project] from local database.
 * @param [localRepository] repository that operated for local database.
 */
class GetProjectsUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    operator fun invoke(): List<Project> {
        return localRepository.getProjectsByResumeId()
    }

}