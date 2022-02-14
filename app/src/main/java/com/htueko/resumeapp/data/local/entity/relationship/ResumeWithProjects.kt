package com.htueko.resumeapp.data.local.entity.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.htueko.resumeapp.data.local.entity.ProjectEntity
import com.htueko.resumeapp.data.local.entity.ResumeEntity

/**
 * one to many relation
 * one resume have many projects
 */
data class ResumeWithProjects(
    @Embedded
    val resumeEntity: ResumeEntity,

    @Relation(
        parentColumn = "resumeId",
        entityColumn = "parentId"
    )
    val projects: List<ProjectEntity>
)