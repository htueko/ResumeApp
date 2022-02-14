package com.htueko.resumeapp.domain.data.local.entity.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.htueko.resumeapp.domain.data.local.entity.EducationEntity
import com.htueko.resumeapp.domain.data.local.entity.ResumeEntity

/**
 * one to many relation
 * one resume have many educations
 */
data class ResumeWithEducations(
    @Embedded
    val resumeEntity: ResumeEntity,

    @Relation(
        parentColumn = "resumeId",
        entityColumn = "parentId"
    )
    val educations: List<EducationEntity>
)