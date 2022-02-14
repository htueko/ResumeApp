package com.htueko.resumeapp.domain.datasource

import com.htueko.resumeapp.domain.model.Resume
import kotlinx.coroutines.flow.Flow

/**
 * single source of data for local database contract.
 */
interface LocalDataSource {

    fun getResumes(): Flow<List<Resume>>

    suspend fun insertOrUpdateResume(resume: Resume)

    suspend fun deleteResume(resume: Resume)

    fun getResumeById(resumeId: Int): Resume?

}