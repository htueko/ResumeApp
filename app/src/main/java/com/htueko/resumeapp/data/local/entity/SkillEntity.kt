package com.htueko.resumeapp.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.htueko.resumeapp.data.local.LocalConstant

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
    val skillId: Int = 0,
    val parentId: Int,
    val skillName: String
)