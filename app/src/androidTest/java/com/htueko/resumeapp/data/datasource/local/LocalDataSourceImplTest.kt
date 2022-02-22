package com.htueko.resumeapp.data.datasource.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.htueko.resumeapp.data.local.dao.ResumeDao
import com.htueko.resumeapp.data.local.database.ResumeDatabase
import com.htueko.resumeapp.data.local.entity.EducationEntity
import com.htueko.resumeapp.data.local.entity.ProjectEntity
import com.htueko.resumeapp.data.local.entity.ResumeEntity
import com.htueko.resumeapp.data.local.entity.SkillEntity
import com.htueko.resumeapp.data.local.entity.WorkEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class LocalDataSourceImplTest {

    private lateinit var database: ResumeDatabase
    private lateinit var dao: ResumeDao

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ResumeDatabase::class.java
        )
            .setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor())
            .allowMainThreadQueries()
            .build()
        dao = database.getResumeDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    private val firstResumeId = 1
    private val secondResumeId = 2

    private val testEducation = EducationEntity(
        educationId = firstResumeId,
        parentId = firstResumeId,
        schoolClass = "school",
        passingYear = 1000,
        percentageOrCgpa = 0.0
    )
    private val testProject = ProjectEntity(
        projectId = firstResumeId,
        parentId = firstResumeId,
        projectName = "project",
        teamSize = 1,
        projectSummary = "summary",
        role = "dev",
        technology = "technology"
    )
    private val testSkill = SkillEntity(
        skillId = firstResumeId,
        parentId = firstResumeId,
        skillName = "skill"
    )
    private val testWork = WorkEntity(
        workId = firstResumeId,
        parentId = firstResumeId,
        companyName = "company",
        duration = 1
    )
    private val testResume = ResumeEntity(
        resumeId = firstResumeId,
        name = "test",
        avatarUrl = "url",
        mobileNumber = "1",
        emailAddress = "abc@mail.com",
        careerObjective = "career",
        totalYearsOfExperience = 1,
        residenceAddress = "Yangon"
    )
    private val testSecondResume = ResumeEntity(
        resumeId = secondResumeId,
        name = "test",
        avatarUrl = "url",
        mobileNumber = "1",
        emailAddress = "abc@mail.com",
        careerObjective = "career",
        totalYearsOfExperience = 1,
        residenceAddress = "Tokyo"
    )

    @Test
    fun getResumesReturnTrue() = runTest {
        dao.insertOrUpdateResume(testResume)
        val response = dao.getResumes()
        assertThat(response).isNotNull()
        response.test {
            val data = this.awaitItem()
            assertThat(data.size).isEqualTo(1)
            assertThat(data[0]).isEqualTo(testResume)
            assertThat(data[0].resumeId).isEqualTo(testResume.resumeId)
        }
    }

    @Test
    fun getEducationsReturnTrue() = runTest {
        dao.insertOrUpdateResume(testResume)
        dao.insertOrUpdateEducations(listOf(testEducation))
        val response = dao.getResumeWithEducations(testResume.resumeId)
        assertThat(response[0].educations[0].parentId).isEqualTo(testResume.resumeId)
        assertThat(response[0].educations[0]).isEqualTo(testEducation)
    }

    @Test
    fun getProjectsReturnTrue() = runTest {
        dao.insertOrUpdateResume(testResume)
        dao.insertOrUpdateProjects(listOf(testProject))
        val response = dao.getResumeWithProjects(testResume.resumeId)
        assertThat(response[0].projects[0].parentId).isEqualTo(testResume.resumeId)
        assertThat(response[0].projects[0]).isEqualTo(testProject)
    }

    @Test
    fun getSkillsReturnTrue() = runTest {
        dao.insertOrUpdateResume(testResume)
        dao.insertOrUpdateSkills(listOf(testSkill))
        val response = dao.getResumeWithSkills(testResume.resumeId)
        assertThat(response[0].skills[0].parentId).isEqualTo(testResume.resumeId)
        assertThat(response[0].skills[0]).isEqualTo(testSkill)
    }

    @Test
    fun getWorksReturnTrue() = runTest {
        dao.insertOrUpdateResume(testResume)
        dao.insertOrUpdateWorks(listOf(testWork))
        val response = dao.getResumeWithWorks(testResume.resumeId)
        assertThat(response[0].works[0].parentId).isEqualTo(testResume.resumeId)
        assertThat(response[0].works[0]).isEqualTo(testWork)
    }

    @Test
    fun deleteResumeReturnTrue() = testScope.runTest {
        dao.insertOrUpdateResume(testResume)
        dao.insertOrUpdateResume(testSecondResume)
        val response = dao.getResumeById(testSecondResume.resumeId)
        val list = dao.getResumes()
        list.test {
            val data = this.awaitItem()
            assertThat(data.size).isEqualTo(2)
            assertThat(data[0]).isEqualTo(testSecondResume)
            assertThat(data[1].resumeId).isEqualTo(testResume.resumeId)
        }
        assertThat(response).isEqualTo(testSecondResume)
        dao.deleteResumeWithCascadeById(testResume.resumeId)
        val remainedResume = dao.getResumeById(testSecondResume.resumeId)
        assertThat(remainedResume).isEqualTo(testSecondResume)
    }
}
