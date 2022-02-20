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

/**
 * Data access object to perform operation related to resume app.
 */
@Dao
abstract class ResumeDao {

    /**
     * to get the list of [ResumeEntity] from database as latest created resume as first.
     *
     * @return list of [ResumeEntity]
     */
    @Query("SELECT * FROM table_resume ORDER BY resumeId DESC")
    abstract fun getResumes(): Flow<List<ResumeEntity>>

    /**
     * to get the [ResumeEntity] or null from database.
     *
     * @param [resumeId] id of the resume.
     * @return an instance of [ResumeEntity] or null
     */
    @Query("SELECT * FROM table_resume where resumeId = :resumeId")
    abstract fun getResumeById(resumeId: Int): ResumeEntity?

    /**
     * to get the list of [ResumeWithEducations] from database as latest created resume as first.
     *
     * @param [resumeId] id of the resume.
     * @return list of [ResumeWithEducations] instances.
     */
    @Transaction
    @Query("SELECT * FROM table_resume WHERE resumeId = :resumeId ORDER BY resumeId DESC")
    abstract fun getResumeWithEducations(resumeId: Int): List<ResumeWithEducations>

    /**
     * to get the list of [ResumeWithProjects] from database as latest created resume as first.
     *
     * @param [resumeId] id of the resume.
     * @return list of [ResumeWithProjects] instances.
     */
    @Transaction
    @Query("SELECT * FROM table_resume WHERE resumeId = :resumeId ORDER BY resumeId DESC")
    abstract fun getResumeWithProjects(resumeId: Int): List<ResumeWithProjects>

    /**
     * to get the list of [ResumeWithSkills] from database as latest created resume as first.
     *
     * @param [resumeId] id of the resume.
     * @return list of [ResumeWithSkills] instances.
     */
    @Transaction
    @Query("SELECT * FROM table_resume WHERE resumeId = :resumeId ORDER BY resumeId DESC")
    abstract fun getResumeWithSkills(resumeId: Int): List<ResumeWithSkills>

    /**
     * to get the list of [ResumeWithWorks] from database as latest created resume as first.
     *
     * @param [resumeId] id of the resume.
     * @return list of [ResumeWithWorks] instances.
     */
    @Transaction
    @Query("SELECT * FROM table_resume WHERE resumeId = :resumeId ORDER BY resumeId DESC")
    abstract fun getResumeWithWorks(resumeId: Int): List<ResumeWithWorks>

    /**
     * to insert or update the [ResumeEntity]
     *
     * @param [resume] an instance of [ResumeEntity]
     * @return id of the latest added [ResumeEntity]
     * @see [https://developer.android.com/reference/java/sql/RowId]
     * @see [https://developer.android.com/training/data-storage/room/accessing-data]
     * @see [https://www.sqlite.org/rowidtable.html]
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOrUpdateResume(resume: ResumeEntity): Long

    /**
     * to insert or update the [EducationEntity]
     *
     * @param [educations] list of [EducationEntity]
     * @return id of the latest added [EducationEntity]
     * @see [https://developer.android.com/reference/java/sql/RowId]
     * @see [https://developer.android.com/training/data-storage/room/accessing-data]
     * @see [https://www.sqlite.org/rowidtable.html]
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOrUpdateEducations(educations: List<EducationEntity>): List<Long>

    /**
     * to insert or update the [ProjectEntity]
     *
     * @param [projects] list of [ProjectEntity]
     * @return id of the latest added [ProjectEntity]
     * @see [https://developer.android.com/reference/java/sql/RowId]
     * @see [https://developer.android.com/training/data-storage/room/accessing-data]
     * @see [https://www.sqlite.org/rowidtable.html]
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOrUpdateProjects(projects: List<ProjectEntity>): List<Long>

    /**
     * to insert or update the [SkillEntity]
     *
     * @param [skills] list of [SkillEntity]
     * @return id of the latest added [SkillEntity]
     * @see [https://developer.android.com/reference/java/sql/RowId]
     * @see [https://developer.android.com/training/data-storage/room/accessing-data]
     * @see [https://www.sqlite.org/rowidtable.html]
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOrUpdateSkills(skills: List<SkillEntity>): List<Long>

    /**
     * to insert or update the [WorkEntity]
     *
     * @param [works] list of [WorkEntity]
     * @return id of the latest added [WorkEntity]
     * @see [https://developer.android.com/reference/java/sql/RowId]
     * @see [https://developer.android.com/training/data-storage/room/accessing-data]
     * @see [https://www.sqlite.org/rowidtable.html]
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOrUpdateWorks(works: List<WorkEntity>): List<Long>

    /**
     * to delete resume from database.
     *
     * @param [resumeId] id of the resume.
     */
    @Query("DELETE FROM table_resume WHERE resumeId = :resumeId")
    abstract fun deleteResumeById(resumeId: Int)

    /**
     * to delete educations from database.
     *
     * @param [resumeId] id of the resume.
     */
    @Query("DELETE FROM table_education WHERE parentId = :resumeId")
    abstract fun deleteEducationsByParentId(resumeId: Int)

    /**
     * to delete projects from database.
     *
     * @param [resumeId] id of the resume.
     */
    @Query("DELETE FROM table_project WHERE parentId = :resumeId")
    abstract fun deleteProjectsByParentId(resumeId: Int)

    /**
     * to delete skills from database.
     *
     * @param [resumeId] id of the resume.
     */
    @Query("DELETE FROM table_skill WHERE parentId = :resumeId")
    abstract fun deleteSkillsByParentId(resumeId: Int)

    /**
     * to delete works from database.
     *
     * @param [resumeId] id of the resume.
     */
    @Query("DELETE FROM table_work WHERE parentId = :resumeId")
    abstract fun deleteWorksByParentId(resumeId: Int)

    /**
     * to delete educations from database.
     *
     * @param [educationId] id of the education.
     */
    @Query("DELETE FROM table_education WHERE educationId = :educationId")
    abstract fun deleteEducationById(educationId: Int)

    /**
     * to delete project from database.
     *
     * @param [projectId] id of the project.
     */
    @Query("DELETE FROM table_project WHERE projectId = :projectId")
    abstract fun deleteProjectById(projectId: Int)

    /**
     * to delete project from database.
     *
     * @param [skillId] id of the skill.
     */
    @Query("DELETE FROM table_skill WHERE skillId = :skillId")
    abstract fun deleteSkillById(skillId: Int)

    /**
     * to delete project from database.
     *
     * @param [workId] id of the work.
     */
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