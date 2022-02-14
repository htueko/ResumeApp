package com.htueko.resumeapp.domain.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.htueko.resumeapp.domain.data.local.LocalConstant

/**
 * Resume model for local database.
 * @property [resumeId] id for the resume, acts as a primary key for the resume too.
 * @property [name] name of the resume author.
 * @property [avatarUrl] image path for the resume.
 * @property [mobileNumber] cell number for the resume.
 * @property [emailAddress] email address for the resume.
 * @property [careerObjective] string representation of the objective of the resume.
 * @property [totalYearsOfExperience] how many years of experience for the resume.
 * @property [residenceAddress] address of the user.
 */
@Entity(tableName = LocalConstant.tableResume)
data class ResumeEntity(
    @PrimaryKey(autoGenerate = true)
    val resumeId: Int,
    val name: String,
    val avatarUrl: String,
    val mobileNumber: String,
    val emailAddress: String,
    val careerObjective: String,
    val totalYearsOfExperience: Int,
    val residenceAddress: String,
)