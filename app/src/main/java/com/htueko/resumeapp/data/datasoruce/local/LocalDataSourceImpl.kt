package com.htueko.resumeapp.data.datasoruce.local

import com.htueko.resumeapp.data.local.dao.ResumeDao
import com.htueko.resumeapp.domain.datasource.LocalDataSource
import com.htueko.resumeapp.domain.model.Resume
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * separation of concern for single source of data for local database.
 * @see [LocalDataSource] contract to implement for this class.
 * @see [resumeDao] data access object for room database.
 */
class LocalDataSourceImpl @Inject constructor(
    private val resumeDao: ResumeDao
) : LocalDataSource{

    override fun getResumes(): Flow<List<Resume>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertOrUpdateResume(resume: Resume) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteResume(resume: Resume) {
        TODO("Not yet implemented")
    }

    override fun getResumeById(resumeId: Int): Resume? {
        TODO("Not yet implemented")
    }

}