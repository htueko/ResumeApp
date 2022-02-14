package com.htueko.resumeapp.data.datasoruce.local

import com.htueko.resumeapp.data.local.dao.ResumeDao
import com.htueko.resumeapp.data.mapper.LocalMapper
import com.htueko.resumeapp.domain.datasource.LocalDataSource
import com.htueko.resumeapp.domain.model.Education
import com.htueko.resumeapp.domain.model.Project
import com.htueko.resumeapp.domain.model.Resume
import com.htueko.resumeapp.domain.model.Skill
import com.htueko.resumeapp.domain.model.Work
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * separation of concern for single source of data for local database.
 * @see [LocalDataSource] contract to implement for this class.
 * @see [resumeDao] data access object for room database.
 * @see [localMapper] to map the data from and to between domain and data layer for local database.
 */
class LocalDataSourceImpl @Inject constructor(
    private val resumeDao: ResumeDao,
    private val localMapper: LocalMapper,
) : LocalDataSource {

    override fun getResumes(): Flow<List<Resume>> {
        val response = resumeDao.getResumes()
        return response.map { localMapper.mapToResumeModels(it) }
    }

    override fun getEducations(): List<Education> {
        val response = resumeDao.getResumeWithEducations()
        return response.flatMap { localMapper.mapToEducationModels(it.educations) }
    }

    override fun getProjects(): List<Project> {
        val response = resumeDao.getResumeWithProjects()
        return response.flatMap { localMapper.mapToProjectModels(it.projects) }
    }

    override fun getSkills(): List<Skill> {
        val response = resumeDao.getResumeWithSkills()
        return response.flatMap { localMapper.mapToSkillModels(it.skills) }
    }

    override fun getWorks(): List<Work> {
        val response = resumeDao.getResumeWithWorks()
        return response.flatMap { localMapper.mapToWorkModels(it.works) }
    }

    override suspend fun insertOrUpdateResume(resume: Resume) {
        withContext(Dispatchers.IO) {
            try {
                resumeDao.insertOrUpdateResume(localMapper.mapToResumeEntity(resume))
            } catch (e: Exception) {
            }
        }
    }

    override suspend fun insertOrUpdateEducations(resumeId: Int, educations: List<Education>) {
        withContext(Dispatchers.IO) {
            try {
                resumeDao.insertOrUpdateEducations(
                    localMapper.mapToEducationEntities(
                        resumeId,
                        educations
                    )
                )
            } catch (e: Exception) {
            }
        }
    }

    override suspend fun insertOrUpdateProjects(resumeId: Int, projects: List<Project>) {
        withContext(Dispatchers.IO) {
            try {
                resumeDao.insertOrUpdateProjects(
                    localMapper.mapToProjectEntities(
                        resumeId,
                        projects
                    )
                )
            } catch (e: Exception) {
            }
        }
    }

    override suspend fun insertOrUpdateSkills(resumeId: Int, skills: List<Skill>) {
        withContext(Dispatchers.IO) {
            try {
                resumeDao.insertOrUpdateSkills(localMapper.mapToSkillEntities(resumeId, skills))
            } catch (e: Exception) {
            }
        }
    }

    override suspend fun insertOrUpdateWorks(resumeId: Int, works: List<Work>) {
        withContext(Dispatchers.IO) {
            try {
                resumeDao.insertOrUpdateWorks(localMapper.mapToWorkEntities(resumeId, works))
            } catch (e: Exception) {
            }
        }
    }

    override suspend fun deleteResume(resume: Resume) {
        withContext(Dispatchers.IO) {
            try {
                resumeDao.deleteResume(localMapper.mapToResumeEntity(resume))
            } catch (e: Exception) {
            }
        }
    }

    override fun getResumeById(resumeId: Int): Resume? {
        val response = resumeDao.getResumeById(resumeId)
        return response?.let { localMapper.mapToResumeModel(it) }
    }

}