package com.htueko.resumeapp.domain.usecase

import com.htueko.resumeapp.domain.model.Resume
import com.htueko.resumeapp.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * to delete [Resume] from local database. actually converted to entity then delete.
 * @param [localRepository] repository that operated for local database.
 */
class DeleteResumeUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(resume: Resume) {
        localRepository.deleteResume(resume)
    }

}