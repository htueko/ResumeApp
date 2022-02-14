package com.htueko.resumeapp.domain.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.htueko.resumeapp.domain.data.local.LocalConstant

/**
 * Skill model for the local database.
 * @property [skillId] id of the skill and primary key.
 * @property [parentId] id of the resume that this model belong to.
 * @property [skillName] string representation of the skill name.
 */
@Entity(
    tableName = LocalConstant.tableSkill,
    foreignKeys = [
        ForeignKey(
            entity = ResumeEntity::class,
            parentColumns = arrayOf("resumeId"),
            childColumns = arrayOf("parentId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SkillEntity(
    @PrimaryKey(autoGenerate = true)
    val skillId: Int,
    val parentId: Int,
    val skillName: String
)