package com.htueko.resumeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.htueko.resumeapp.data.local.entity.EducationEntity
import com.htueko.resumeapp.data.local.entity.ProjectEntity
import com.htueko.resumeapp.data.local.entity.ResumeEntity
import com.htueko.resumeapp.data.local.entity.SkillEntity
import com.htueko.resumeapp.data.local.entity.WorkEntity
import com.htueko.resumeapp.data.local.entity.relationship.ResumeWithEducations
import com.htueko.resumeapp.data.local.entity.relationship.ResumeWithProjects
import com.htueko.resumeapp.data.local.entity.relationship.ResumeWithSkills
import com.htueko.resumeapp.data.local.entity.relationship.ResumeWithWorks
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ResumeDao {

    @Query("SELECT * FROM table_resume ORDER BY resumeId DESC")
    abstract fun getResumes(): Flow<List<ResumeEntity>>

    @Query("SELECT * FROM table_resume where resumeId = :resumeId")
    abstract fun getResumeById(resumeId: Int): ResumeEntity?

    @Transaction
    @Query("SELECT * FROM table_resume ORDER BY resumeId DESC")
    abstract fun getResumeWithEducations(): List<ResumeWithEducations>

    @Transaction
    @Query("SELECT * FROM table_resume ORDER BY resumeId DESC")
    abstract fun getResumeWithProjects(): List<ResumeWithProjects>

    @Transaction
    @Query("SELECT * FROM table_resume ORDER BY resumeId DESC")
    abstract fun getResumeWithSkills(): List<ResumeWithSkills>

    @Transaction
    @Query("SELECT * FROM table_resume ORDER BY resumeId DESC")
    abstract fun getResumeWithWorks(): List<ResumeWithWorks>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOrUpdateResume(resume: ResumeEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOrUpdateEducations(educations: List<EducationEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOrUpdateProjects(projects: List<ProjectEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOrUpdateSkills(skills: List<SkillEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOrUpdateWorks(works: List<WorkEntity>): List<Long>

    @Query("DELETE FROM table_resume WHERE resumeId = :resumeId")
    abstract fun deleteResumeById(resumeId: Int)

    @Query("DELETE FROM table_education WHERE parentId = :resumeId")
    abstract fun deleteEducationsByParentId(resumeId: Int)

    @Query("DELETE FROM table_project WHERE parentId = :resumeId")
    abstract fun deleteProjectsByParentId(resumeId: Int)

    @Query("DELETE FROM table_skill WHERE parentId = :resumeId")
    abstract fun deleteSkillsByParentId(resumeId: Int)

    @Query("DELETE FROM table_work WHERE parentId = :resumeId")
    abstract fun deleteWorksByParentId(resumeId: Int)

    @Query("DELETE FROM table_education WHERE educationId = :educationId")
    abstract fun deleteEducationById(educationId: Int)

    @Query("DELETE FROM table_project WHERE projectId = :projectId")
    abstract fun deleteProjectById(projectId: Int)

    @Query("DELETE FROM table_skill WHERE skillId = :skillId")
    abstract fun deleteSkillById(skillId: Int)

    @Query("DELETE FROM table_work WHERE workId = :workId")
    abstract fun deleteWorkById(workId: Int)

    // delete cascade walk around because abstract class usage.
    // https://stackoverflow.com/questions/70134307/cascade-delete-in-android-room-database-kotlin
    @Transaction
    @Query("")
    fun deleteResumeWithCascadeById(resumeId: Int) {
        deleteEducationsByParentId(resumeId)
        deleteProjectsByParentId(resumeId)
        deleteSkillsByParentId(resumeId)
        deleteWorksByParentId(resumeId)
        deleteResumeById(resumeId)
    }


}