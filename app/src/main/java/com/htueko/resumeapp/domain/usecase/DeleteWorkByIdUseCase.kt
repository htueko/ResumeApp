package com.htueko.resumeapp.domain.usecase

import com.htueko.resumeapp.domain.model.Work
import com.htueko.resumeapp.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * to delete [Work] from local database. actually converted to entity then delete.
 * @param [localRepository] repository that operated for local database.
 */
class DeleteWorkByIdUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(work: Work) {
        localRepository.deleteWorkById(work)
    }

}