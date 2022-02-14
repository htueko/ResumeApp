package com.htueko.resumeapp.data.local.entity.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.htueko.resumeapp.data.local.entity.ResumeEntity
import com.htueko.resumeapp.data.local.entity.WorkEntity

/**
 * one to many relation
 * one resume have many works
 */
data class ResumeWithWorks(
    @Embedded
    val resumeEntity: ResumeEntity,

    @Relation(
        parentColumn = "resumeId",
        entityColumn = "parentId"
    )
    val works: List<WorkEntity>
)