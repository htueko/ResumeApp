package com.htueko.resumeapp.domain.usecase

import com.htueko.resumeapp.domain.model.Skill
import com.htueko.resumeapp.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * to get the list of [Skill] from local database.
 * @param [localRepository] repository that operated for local database.
 */
class GetSkillsUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    operator fun invoke(): List<Skill> {
        return localRepository.getSkillsByResumeId()
    }

}