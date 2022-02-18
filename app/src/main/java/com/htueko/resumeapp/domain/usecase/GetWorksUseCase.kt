package com.htueko.resumeapp.domain.usecase

import com.htueko.resumeapp.domain.model.Work
import com.htueko.resumeapp.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * to get the list of [Work] from local database.
 * @param [localRepository] repository that operated for local database.
 */
class GetWorksUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    operator fun invoke(): List<Work> {
        return localRepository.getWorksByResumeId()
    }

}