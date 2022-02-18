package com.htueko.resumeapp.domain.usecase

import com.htueko.resumeapp.domain.model.Work
import com.htueko.resumeapp.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * to insert or update the list of [Work] instance.
 * @param [localRepository] repository that operated for local database.
 */
class InsertOrUpdateWorksUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(resumeId: Int, works: List<Work>): Int? {
        return localRepository.insertOrUpdateWorks(resumeId, works)
    }

}