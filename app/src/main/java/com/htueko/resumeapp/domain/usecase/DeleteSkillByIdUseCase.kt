package com.htueko.resumeapp.domain.usecase

import com.htueko.resumeapp.domain.model.Skill
import com.htueko.resumeapp.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * to delete [Skill] from local database. actually converted to entity then delete.
 * @param [localRepository] repository that operated for local database.
 */
class DeleteSkillByIdUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(skill: Skill) {
        localRepository.deleteSkillById(skill)
    }

}