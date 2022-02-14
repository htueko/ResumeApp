package com.htueko.resumeapp.data.local.entity.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.htueko.resumeapp.data.local.entity.ResumeEntity
import com.htueko.resumeapp.data.local.entity.SkillEntity

/**
 * one to many relation
 * one resume have many skills
 */
data class ResumeWithSkills(
    @Embedded
    val resumeEntity: ResumeEntity,

    @Relation(
        parentColumn = "resumeId",
        entityColumn = "parentId"
    )
    val skills: List<SkillEntity>
)