package com.htueko.resumeapp.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.htueko.resumeapp.data.local.LocalConstant

/**
 * Project model for local database.
 * @property [projectId] id of the project model and primary key.
 * @property [parentId] id of the resume that this model belong to.
 * @property [projectName] name of the project.
 * @property [teamSize] size of the project.
 * @property [projectSummary] summary of the project.
 * @property [role] the position for the project.
 * @property [technology] used technologies for the project.
 */
@Entity(
    tableName = LocalConstant.tableProject,
    foreignKeys = [
        ForeignKey(
            entity = ResumeEntity::class,
            parentColumns = arrayOf("resumeId"),
            childColumns = arrayOf("parentId"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [Index("parentId")]
)
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true)
    val projectId: Int = 0,
    val parentId: Int,
    val projectName: String,
    val teamSize: Int,
    val projectSummary: String,
    val role: String,
    val technology: String
)
