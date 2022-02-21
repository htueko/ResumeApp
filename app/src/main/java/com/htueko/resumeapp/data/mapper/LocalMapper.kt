package com.htueko.resumeapp.data.mapper

import com.htueko.resumeapp.data.local.entity.EducationEntity
import com.htueko.resumeapp.data.local.entity.ProjectEntity
import com.htueko.resumeapp.data.local.entity.ResumeEntity
import com.htueko.resumeapp.data.local.entity.SkillEntity
import com.htueko.resumeapp.data.local.entity.WorkEntity
import com.htueko.resumeapp.domain.model.Education
import com.htueko.resumeapp.domain.model.Project
import com.htueko.resumeapp.domain.model.Resume
import com.htueko.resumeapp.domain.model.Skill
import com.htueko.resumeapp.domain.model.Work

/**
 * to map between entity and model
 */
@Suppress("TooManyFunctions")
class LocalMapper {

    fun mapToResumeEntity(resume: Resume): ResumeEntity {
        return ResumeEntity(
            name = resume.name,
            avatarUrl = resume.avatarUrl,
            mobileNumber = resume.mobileNumber,
            emailAddress = resume.emailAddress,
            careerObjective = resume.careerObjective,
            totalYearsOfExperience = resume.totalYearsOfExperience,
            residenceAddress = resume.address,
        )
    }

    fun mapToResumeModel(resumeEntity: ResumeEntity): Resume {
        return Resume(
            resumeId = resumeEntity.resumeId,
            name = resumeEntity.name,
            avatarUrl = resumeEntity.avatarUrl,
            mobileNumber = resumeEntity.mobileNumber,
            emailAddress = resumeEntity.emailAddress,
            careerObjective = resumeEntity.careerObjective,
            totalYearsOfExperience = resumeEntity.totalYearsOfExperience,
            address = resumeEntity.residenceAddress,
        )
    }

    fun mapToResumeModels(resumeEntities: List<ResumeEntity>): List<Resume> {
        val data = resumeEntities.map { data ->
            Resume(
                resumeId = data.resumeId,
                name = data.name,
                avatarUrl = data.avatarUrl,
                mobileNumber = data.mobileNumber,
                emailAddress = data.emailAddress,
                careerObjective = data.careerObjective,
                totalYearsOfExperience = data.totalYearsOfExperience,
                address = data.residenceAddress,
            )
        }
        return data
    }

    fun mapToEducationEntities(resumeId: Int, educations: List<Education>): List<EducationEntity> {
        val data = educations.map { education ->
            EducationEntity(
                parentId = resumeId,
                schoolClass = education.schoolClass,
                passingYear = education.passingYear,
                percentageOrCgpa = education.percentageOrCgpa,
            )
        }
        return data
    }

    fun mapToEducationEntity(education: Education): EducationEntity {
        return EducationEntity(
            educationId = education.educationId,
            parentId = education.parentId, // this field is not true, not resume id.
            schoolClass = education.schoolClass,
            passingYear = education.passingYear,
            percentageOrCgpa = education.percentageOrCgpa,
        )
    }

    fun mapToEducationModels(educations: List<EducationEntity>): List<Education> {
        val data = educations.map { education ->
            Education(
                educationId = education.educationId,
                parentId = education.parentId,
                schoolClass = education.schoolClass,
                passingYear = education.passingYear,
                percentageOrCgpa = education.percentageOrCgpa,
            )
        }
        return data
    }

    fun mapToProjectEntities(resumeId: Int, projects: List<Project>): List<ProjectEntity> {
        val data = projects.map { project ->
            ProjectEntity(
                parentId = resumeId,
                projectName = project.projectName,
                teamSize = project.teamSize,
                projectSummary = project.projectSummary,
                role = project.role,
                technology = project.technology,
            )
        }
        return data
    }

    fun mapToProjectEntity(project: Project): ProjectEntity {
        return ProjectEntity(
            parentId = project.projectId, // this field is not true, not resume id.
            projectName = project.projectName,
            teamSize = project.teamSize,
            projectSummary = project.projectSummary,
            role = project.role,
            technology = project.technology,
        )
    }

    fun mapToProjectModels(projects: List<ProjectEntity>): List<Project> {
        val data = projects.map { project ->
            Project(
                projectId = project.projectId,
                parentId = project.parentId,
                projectName = project.projectName,
                teamSize = project.teamSize,
                projectSummary = project.projectSummary,
                role = project.role,
                technology = project.technology,
            )
        }
        return data
    }

    fun mapToSkillEntities(resumeId: Int, skills: List<Skill>): List<SkillEntity> {
        val data = skills.map { skill ->
            SkillEntity(
                parentId = resumeId,
                skillId = skill.skillId,
                skillName = skill.skillName
            )
        }
        return data
    }

    fun mapToSkillEntity(skill: Skill): SkillEntity {
        return SkillEntity(
            parentId = skill.skillId, // this field is not true, not resume id.
            skillId = skill.skillId,
            skillName = skill.skillName
        )
    }

    fun mapToSkillModels(skills: List<SkillEntity>): List<Skill> {
        val data = skills.map { skill ->
            Skill(
                skillId = skill.skillId,
                parentId = skill.parentId,
                skillName = skill.skillName
            )
        }
        return data
    }

    fun mapToWorkEntities(resumeId: Int, works: List<Work>): List<WorkEntity> {
        val data = works.map { work ->
            WorkEntity(
                parentId = resumeId,
                companyName = work.companyName,
                duration = work.duration,
            )
        }
        return data
    }

    fun mapToWorkEntity(work: Work): WorkEntity {
        return WorkEntity(
            parentId = work.workId, // this field is not true, not resume id.
            companyName = work.companyName,
            duration = work.duration,
        )
    }

    fun mapToWorkModels(works: List<WorkEntity>): List<Work> {
        val data = works.map { work ->
            Work(
                workId = work.workId,
                parentId = work.parentId,
                companyName = work.companyName,
                duration = work.duration,
            )
        }
        return data
    }
}
