package com.htueko.resumeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
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
    abstract fun insertOrUpdateResume(resume: ResumeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrUpdateEducations(educations: List<EducationEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrUpdateProjects(projects: List<ProjectEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrUpdateSkills(skills: List<SkillEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrUpdateWorks(works: List<WorkEntity>)

    @Delete
    abstract suspend fun deleteResume(resume: ResumeEntity)


}