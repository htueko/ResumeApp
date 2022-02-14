package com.htueko.resumeapp.data.mapper

import com.htueko.resumeapp.data.local.entity.EducationEntity
import com.htueko.resumeapp.data.local.entity.ProjectEntity
import com.htueko.resumeapp.data.local.entity.ResumeEntity
import com.htueko.resumeapp.data.local.entity.SkillEntity
import com.htueko.resumeapp.data.local.entity.WorkEntity
import com.htueko.resumeapp.data.local.entity.relationship.ResumeDetail
import com.htueko.resumeapp.domain.model.Education
import com.htueko.resumeapp.domain.model.Project
import com.htueko.resumeapp.domain.model.Resume
import com.htueko.resumeapp.domain.model.Skill
import com.htueko.resumeapp.domain.model.Work

/**
 * to map between entity and model
 */
class LocalMapper {

    fun mapToResumeEntity(resume: Resume): ResumeEntity {
        return ResumeEntity(
            resumeId = resume.resumeId,
            name = resume.name,
            avatarUrl = resume.avatarUrl,
            mobileNumber = resume.mobileNumber,
            emailAddress = resume.emailAddress,
            careerObjective = resume.careerObjective,
            totalYearsOfExperience = resume.totalYearsOfExperience,
            residenceAddress = resume.address,
        )
    }

    fun mapToResumeModels(resumeEntities: List<ResumeDetail>): List<Resume> {
        val data = resumeEntities.map { data ->
            val resume = data.resume
            val educations = data.educations.educations
            val projects = data.projects.projects
            val skills = data.skills.skills
            val works = data.works.works
            Resume(
                resumeId = resume.resumeId,
                name = resume.name,
                avatarUrl = resume.avatarUrl,
                mobileNumber = resume.mobileNumber,
                emailAddress = resume.emailAddress,
                careerObjective = resume.careerObjective,
                totalYearsOfExperience = resume.totalYearsOfExperience,
                address = resume.residenceAddress,
                educations = mapToEducationModel(educations),
                projects = mapToProjectModel(projects),
                skills = mapToSkillModel(skills),
                works = mapToWorkModel(works),
            )
        }
        return data
    }

    private fun mapToEducationEntity(resume: Resume): List<EducationEntity> {
        val data = resume.educations.map { education ->
            EducationEntity(
                educationId = resume.resumeId,
                parentId = resume.resumeId,
                schoolClass = education.schoolClass,
                passingYear = education.passingYear,
                percentageOrCgpa = education.percentageOrCgpa,
            )
        }
        return data
    }

    private fun mapToEducationModel(educations: List<EducationEntity>): List<Education> {
        val data = educations.map { education ->
            Education(
                schoolClass = education.schoolClass,
                passingYear = education.passingYear,
                percentageOrCgpa = education.percentageOrCgpa,
            )
        }
        return data
    }

    private fun mapToProjectEntity(resume: Resume): List<ProjectEntity> {
        val data = resume.projects.map { project ->
            ProjectEntity(
                projectId = resume.resumeId,
                parentId = resume.resumeId,
                projectName = project.projectName,
                teamSize = project.teamSize,
                projectSummary = project.projectSummary,
                role = project.role,
                technology = project.technology,
            )
        }
        return data
    }

    private fun mapToProjectModel(projects: List<ProjectEntity>): List<Project> {
        val data = projects.map { project ->
            Project(
                projectName = project.projectName,
                teamSize = project.teamSize,
                projectSummary = project.projectSummary,
                role = project.role,
                technology = project.technology,
            )
        }
        return data
    }

    private fun mapToSkillEntity(resume: Resume): List<SkillEntity> {
        val data = resume.skills.map { skill ->
            SkillEntity(
                skillId = resume.resumeId,
                parentId = resume.resumeId,
                skillName = skill.skillName
            )
        }
        return data
    }

    private fun mapToSkillModel(skills: List<SkillEntity>): List<Skill> {
        val data = skills.map { skill ->
            Skill(skillName = skill.skillName)
        }
        return data
    }

    private fun mapToWorkEntity(resume: Resume): List<WorkEntity> {
        val data = resume.works.map { work ->
            WorkEntity(
                workId = resume.resumeId,
                parentId = resume.resumeId,
                companyName = work.companyName,
                duration = work.duration,
            )
        }
        return data
    }

    private fun mapToWorkModel(works: List<WorkEntity>): List<Work> {
        val data = works.map { work ->
            Work(
                companyName = work.companyName,
                duration = work.duration,
            )
        }
        return data
    }

}