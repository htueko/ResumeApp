package com.htueko.resumeapp.domain.usecase

import com.htueko.resumeapp.domain.model.Skill
import com.htueko.resumeapp.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * to insert or update the list of [Skill] instance.
 * @param [localRepository] repository that operated for local database.
 */
class InsertOrUpdateSkillsUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(resumeId: Int, skills: List<Skill>) {
        localRepository.insertOrUpdateSkills(resumeId, skills)
    }

}