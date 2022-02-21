package com.htueko.resumeapp.data.repository.local

import com.htueko.resumeapp.domain.datasource.LocalDataSource
import com.htueko.resumeapp.domain.model.Education
import com.htueko.resumeapp.domain.model.Project
import com.htueko.resumeapp.domain.model.Resume
import com.htueko.resumeapp.domain.model.Skill
import com.htueko.resumeapp.domain.model.Work
import com.htueko.resumeapp.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * implementation of [LocalRepository].
 * @see [localDataSource] data source for local database.
 * @see [LocalRepository] contract to implemented in this class.
 */
@Suppress("TooManyFunctions")
class LocalRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : LocalRepository {
    override fun getResumes(): Flow<List<Resume>> {
        return localDataSource.getResumes()
    }

    override suspend fun getEducationsByResumeId(resumeId: Int): List<Education> {
        return localDataSource.getEducationByResumeId(resumeId)
    }

    override suspend fun getProjectsByResumeId(resumeId: Int): List<Project> {
        return localDataSource.getProjectsByResumeId(resumeId)
    }

    override suspend fun getSkillsByResumeId(resumeId: Int): List<Skill> {
        return localDataSource.getSkillsByResumeId(resumeId)
    }

    override suspend fun getWorksByResumeId(resumeId: Int): List<Work> {
        return localDataSource.getWorksByResumeId(resumeId)
    }

    override suspend fun insertOrUpdateResume(resume: Resume): Int? {
        return localDataSource.insertOrUpdateResume(resume)
    }

    override suspend fun insertOrUpdateEducations(
        resumeId: Int,
        educations: List<Education>
    ): Int? {
        return localDataSource.insertOrUpdateEducations(resumeId, educations)
    }

    override suspend fun insertOrUpdateProjects(resumeId: Int, projects: List<Project>): Int? {
        return localDataSource.insertOrUpdateProjects(resumeId, projects)
    }

    override suspend fun insertOrUpdateSkills(resumeId: Int, skills: List<Skill>): Int? {
        return localDataSource.insertOrUpdateSkills(resumeId, skills)
    }

    override suspend fun insertOrUpdateWorks(resumeId: Int, works: List<Work>): Int? {
        return localDataSource.insertOrUpdateWorks(resumeId, works)
    }

    override suspend fun deleteResume(resume: Resume) {
        localDataSource.deleteResumeCascadeByResumeId(resume)
    }

    override suspend fun deleteEducationById(education: Education) {
        localDataSource.deleteEducationById(education)
    }

    override suspend fun deleteProjectById(project: Project) {
        localDataSource.deleteProjectById(project)
    }

    override suspend fun deleteSkillById(skill: Skill) {
        localDataSource.deleteSkillById(skill)
    }

    override suspend fun deleteWorkById(work: Work) {
        localDataSource.deleteWorkById(work)
    }

    override fun getResumeById(resumeId: Int): Resume? {
        return localDataSource.getResumeById(resumeId)
    }
}
