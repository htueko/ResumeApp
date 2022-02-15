package com.htueko.resumeapp.domain.usecase

import com.htueko.resumeapp.domain.model.Education
import com.htueko.resumeapp.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * to insert or update the list of [Education] instance.
 * @param [localRepository] repository that operated for local database.
 */
class InsertOrUpdateEducationsUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(resumeId: Int, educations: List<Education>) {
        localRepository.insertOrUpdateEducations(resumeId, educations)
    }

}