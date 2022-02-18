package com.htueko.resumeapp.domain.usecase

import com.htueko.resumeapp.domain.model.Education
import com.htueko.resumeapp.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * to get the list of [Education] from local database.
 * @param [localRepository] repository that operated for local database.
 */
class GetEducationsUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    operator fun invoke(): List<Education> {
        return localRepository.getEducationsByResumeId()
    }

}