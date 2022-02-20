package com.htueko.resumeapp.data.datasource.local

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

    override suspend fun getEducationByResumeId(resumeId: Int): List<Education> =
        withContext(Dispatchers.IO) {
            try {
                val response = resumeDao.getResumeWithEducations(resumeId)
                response.flatMap { localMapper.mapToEducationModels(it.educations) }
            } catch (e: Exception) {
                emptyList<Education>()
            }
        }

    override suspend fun getProjectsByResumeId(resumeId: Int): List<Project> =
        withContext(Dispatchers.IO) {
            try {
                val response = resumeDao.getResumeWithProjects(resumeId)
                response.flatMap { localMapper.mapToProjectModels(it.projects) }
            } catch (e: Exception) {
                emptyList<Project>()
            }
        }

    override suspend fun getSkillsByResumeId(resumeId: Int): List<Skill> =
        withContext(Dispatchers.IO) {
            try {
                val response = resumeDao.getResumeWithSkills(resumeId)
                response.flatMap { localMapper.mapToSkillModels(it.skills) }
            } catch (e: Exception) {
                emptyList<Skill>()
            }
        }

    override suspend fun getWorksByResumeId(resumeId: Int): List<Work> =
        withContext(Dispatchers.IO) {
            try {
                val response = resumeDao.getResumeWithWorks(resumeId)
                response.flatMap { localMapper.mapToWorkModels(it.works) }
            } catch (e: Exception) {
                emptyList<Work>()
            }
        }

    override suspend fun insertOrUpdateResume(resume: Resume): Int? =
        withContext(Dispatchers.IO) {
            try {
                resumeDao.insertOrUpdateResume(
                    localMapper.mapToResumeEntity(
                        resume
                    )
                ).toInt()
            } catch (e: Exception) {
                null
            }
        }

    override suspend fun insertOrUpdateEducations(
        resumeId: Int,
        educations: List<Education>
    ): Int? =
        withContext(Dispatchers.IO) {
            try {
                val data = resumeDao.insertOrUpdateEducations(
                    localMapper.mapToEducationEntities(
                        resumeId,
                        educations
                    )
                )
                // return the rowId of the firs index
                data[0].toInt()
            } catch (e: Exception) {
                null
            }
        }

    override suspend fun insertOrUpdateProjects(resumeId: Int, projects: List<Project>): Int? =
        withContext(Dispatchers.IO) {
            try {
                val data = resumeDao.insertOrUpdateProjects(
                    localMapper.mapToProjectEntities(
                        resumeId,
                        projects
                    )
                )
                // return the rowId of the firs index
                data[0].toInt()
            } catch (e: Exception) {
                null
            }
        }

    override suspend fun insertOrUpdateSkills(resumeId: Int, skills: List<Skill>): Int? =
        withContext(Dispatchers.IO) {
            try {
                val data =
                    resumeDao.insertOrUpdateSkills(localMapper.mapToSkillEntities(resumeId, skills))
                // return the rowId of the firs index
                data[0].toInt()
            } catch (e: Exception) {
                null
            }
        }

    override suspend fun insertOrUpdateWorks(resumeId: Int, works: List<Work>): Int? =
        withContext(Dispatchers.IO) {
            try {
                val data =
                    resumeDao.insertOrUpdateWorks(localMapper.mapToWorkEntities(resumeId, works))
                // return the rowId of the firs index
                data[0].toInt()
            } catch (e: Exception) {
                null
            }
        }

    override suspend fun deleteResumeCascadeByResumeId(resume: Resume) {
        withContext(Dispatchers.IO) {
            try {
                val data = localMapper.mapToResumeEntity(resume)
                resumeDao.deleteResumeWithCascadeById(resume.resumeId)
            } catch (e: Exception) {
            }
        }
    }

    override suspend fun deleteEducationById(education: Education) {
        withContext(Dispatchers.IO) {
            try {
                val data = localMapper.mapToEducationEntity(education)
                resumeDao.deleteEducationById(data.educationId)
            } catch (e: Exception) {
            }
        }
    }

    override suspend fun deleteProjectById(project: Project) {
        withContext(Dispatchers.IO) {
            try {
                val data = localMapper.mapToProjectEntity(project)
                resumeDao.deleteProjectById(data.projectId)
            } catch (e: Exception) {
            }
        }
    }

    override suspend fun deleteSkillById(skill: Skill) {
        withContext(Dispatchers.IO) {
            try {
                val data = localMapper.mapToSkillEntity(skill)
                resumeDao.deleteSkillById(data.skillId)
            } catch (e: Exception) {
            }
        }
    }

    override suspend fun deleteWorkById(work: Work) {
        withContext(Dispatchers.IO) {
            try {
                val data = localMapper.mapToWorkEntity(work)
                resumeDao.deleteWorkById(data.workId)
            } catch (e: Exception) {
            }
        }
    }

    override fun getResumeById(resumeId: Int): Resume? {
        val response = resumeDao.getResumeById(resumeId)
        return response?.let { localMapper.mapToResumeModel(it) }
    }
}
