package com.htueko.resumeapp.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.htueko.resumeapp.data.local.LocalConstant

/**
 * Work model for local database.
 * @property [workId] id of the work and primary key for this table.
 * @property [parentId] id of the resume that this model belong to.
 * @property [companyName] string representation of the company name.
 * @property [duration] integer representation of the stay at the company as months.
 */
@Entity(
    tableName = LocalConstant.tableWork,
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
data class WorkEntity(
    @PrimaryKey(autoGenerate = true)
    val workId: Int = 0,
    val parentId: Int,
    val companyName: String,
    val duration: Int
)
