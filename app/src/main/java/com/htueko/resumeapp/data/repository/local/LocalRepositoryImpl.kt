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
class LocalRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : LocalRepository {
    override fun getResumes(): Flow<List<Resume>> {
        return localDataSource.getResumes()
    }

    override fun getEducations(): List<Education> {
        return localDataSource.getEducations()
    }

    override fun getProjects(): List<Project> {
        return localDataSource.getProjects()
    }

    override fun getSkills(): List<Skill> {
        return localDataSource.getSkills()
    }

    override fun getWorks(): List<Work> {
        return localDataSource.getWorks()
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