package com.htueko.resumeapp.domain.usecase

import com.htueko.resumeapp.domain.model.Resume
import com.htueko.resumeapp.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * to get the list of [Resume] from local database.
 * @param [localRepository] repository that operated for local database.
 */
class GetResumeUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    operator fun invoke(): Flow<List<Resume>> {
        return localRepository.getResumes()
    }

}