package com.htueko.resumeapp.domain.usecase

import com.htueko.resumeapp.domain.model.Project
import com.htueko.resumeapp.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * to delete [Project] from local database. actually converted to entity then delete.
 * @param [localRepository] repository that operated for local database.
 */
class DeleteProjectByIdUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(project: Project) {
        localRepository.deleteProjectById(project)
    }

}