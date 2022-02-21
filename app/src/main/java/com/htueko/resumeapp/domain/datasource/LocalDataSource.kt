package com.htueko.resumeapp.domain.datasource

import com.htueko.resumeapp.domain.model.Education
import com.htueko.resumeapp.domain.model.Project
import com.htueko.resumeapp.domain.model.Resume
import com.htueko.resumeapp.domain.model.Skill
import com.htueko.resumeapp.domain.model.Work
import kotlinx.coroutines.flow.Flow

/**
 * single source of data for local database contract.
 */
@Suppress("TooManyFunctions")
interface LocalDataSource {

    fun getResumes(): Flow<List<Resume>>

    suspend fun getEducationByResumeId(resumeId: Int): List<Education>

    suspend fun getProjectsByResumeId(resumeId: Int): List<Project>

    suspend fun getSkillsByResumeId(resumeId: Int): List<Skill>

    suspend fun getWorksByResumeId(resumeId: Int): List<Work>

    suspend fun insertOrUpdateResume(resume: Resume): Int?

    suspend fun insertOrUpdateEducations(resumeId: Int, educations: List<Education>): Int?

    suspend fun insertOrUpdateProjects(resumeId: Int, projects: List<Project>): Int?

    suspend fun insertOrUpdateSkills(resumeId: Int, skills: List<Skill>): Int?

    suspend fun insertOrUpdateWorks(resumeId: Int, works: List<Work>): Int?

    suspend fun deleteResumeCascadeByResumeId(resume: Resume)

    suspend fun deleteEducationById(education: Education)

    suspend fun deleteProjectById(project: Project)

    suspend fun deleteSkillById(skill: Skill)

    suspend fun deleteWorkById(work: Work)

    fun getResumeById(resumeId: Int): Resume?
}
