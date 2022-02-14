package com.htueko.resumeapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.htueko.resumeapp.data.local.LocalConstant
import com.htueko.resumeapp.data.local.dao.ResumeDao
import com.htueko.resumeapp.data.local.entity.EducationEntity
import com.htueko.resumeapp.data.local.entity.ProjectEntity
import com.htueko.resumeapp.data.local.entity.ResumeEntity
import com.htueko.resumeapp.data.local.entity.SkillEntity
import com.htueko.resumeapp.data.local.entity.WorkEntity

@Database(
    entities = [
        EducationEntity::class,
        ProjectEntity::class,
        ResumeEntity::class,
        SkillEntity::class,
        WorkEntity::class,
    ], version = LocalConstant.DB_VERSION, exportSchema = false
)
abstract class ResumeDatabase : RoomDatabase() {
    abstract fun getResumeDao(): ResumeDao
}