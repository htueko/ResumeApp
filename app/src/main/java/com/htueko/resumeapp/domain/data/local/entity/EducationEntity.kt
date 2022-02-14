package com.htueko.resumeapp.domain.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.htueko.resumeapp.domain.data.local.LocalConstant

/**
 * Education model for the local database.
 * @property [educationId] id of the education and primary key.
 * @property [parentId] id of the resume that this model belong to.
 * @property [schoolClass] class of the education.
 * @property [passingYear] when did the user passed the school.
 * @property [percentageOrCgpa] percentage or CGPA.
 */
@Entity(
    tableName = LocalConstant.tableEducation,
    foreignKeys = [
        ForeignKey(
            entity = ResumeEntity::class,
            parentColumns = arrayOf("resumeId"),
            childColumns = arrayOf("parentId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class EducationEntity(
    @PrimaryKey(autoGenerate = true)
    val educationId: Int,
    val parentId: Int,
    val schoolClass: String,
    val passingYear: Int,
    val percentageOrCgpa: Double
)