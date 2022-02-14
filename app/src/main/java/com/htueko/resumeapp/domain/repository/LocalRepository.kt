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
interface LocalRepository {

    fun getResumes(): Flow<List<Resume>>

    fun getEducations(): List<Education>

    fun getProjects(): List<Project>

    fun getSkills(): List<Skill>

    fun getWorks(): List<Work>

    suspend fun insertOrUpdateResume(resume: Resume)

    suspend fun insertOrUpdateEducations(resumeId: Int, educations: List<Education>)

    suspend fun insertOrUpdateProjects(resumeId: Int, projects: List<Project>)

    suspend fun insertOrUpdateSkills(resumeId: Int, skills: List<Skill>)

    suspend fun insertOrUpdateWorks(resumeId: Int, works: List<Work>)

    suspend fun deleteResume(resume: Resume)

    fun getResumeById(resumeId: Int): Resume?

}