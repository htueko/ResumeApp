package com.htueko.resumeapp.domain.usecase

import com.htueko.resumeapp.domain.model.Education
import com.htueko.resumeapp.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * to delete [Education] from local database. actually converted to entity then delete.
 * @param [localRepository] repository that operated for local database.
 */
class DeleteEducationByIdUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(education: Education) {
        localRepository.deleteEducationById(education)
    }

}