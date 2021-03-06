package com.htueko.resumeapp.domain.repository

import com.htueko.resumeapp.domain.model.Education
import com.htueko.resumeapp.domain.model.Project
import com.htueko.resumeapp.domain.model.Resume
import com.htueko.resumeapp.domain.model.Skill
import com.htueko.resumeapp.domain.model.Work
import kotlinx.coroutines.flow.Flow

/**
 * contract for repository pattern for local database
 */
@Suppress("TooManyFunctions")
interface LocalRepository {

    fun getResumes(): Flow<List<Resume>>

    suspend fun getEducationsByResumeId(resumeId: Int): List<Education>

    suspend fun getProjectsByResumeId(resumeId: Int): List<Project>

    suspend fun getSkillsByResumeId(resumeId: Int): List<Skill>

    suspend fun getWorksByResumeId(resumeId: Int): List<Work>

    suspend fun insertOrUpdateResume(resume: Resume): Int?

    suspend fun insertOrUpdateEducations(resumeId: Int, educations: List<Education>): Int?

    suspend fun insertOrUpdateProjects(resumeId: Int, projects: List<Project>): Int?

    suspend fun insertOrUpdateSkills(resumeId: Int, skills: List<Skill>): Int?

    suspend fun insertOrUpdateWorks(resumeId: Int, works: List<Work>): Int?

    suspend fun deleteResume(resume: Resume)

    suspend fun deleteEducationById(education: Education)

    suspend fun deleteProjectById(project: Project)

    suspend fun deleteSkillById(skill: Skill)

    suspend fun deleteWorkById(work: Work)

    fun getResumeById(resumeId: Int): Resume?
}
