package com.htueko.resumeapp.domain.usecase

import com.htueko.resumeapp.domain.model.Resume
import com.htueko.resumeapp.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * to insert or update the [Resume] instance
 * @param [localRepository] repository that operated for local database
 */
class InsertOrUpdateResumeUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(resume: Resume): Int? {
        return localRepository.insertOrUpdateResume(resume)
    }
}
