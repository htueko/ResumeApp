package com.htueko.resumeapp.domain.data.di

import android.content.Context
import androidx.room.Room
import com.htueko.resumeapp.domain.data.local.LocalConstant
import com.htueko.resumeapp.domain.data.local.dao.ResumeDao
import com.htueko.resumeapp.domain.data.local.database.ResumeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    companion object {

        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context
        ): ResumeDatabase {
            return Room.databaseBuilder(
                context,
                ResumeDatabase::class.java,
                LocalConstant.DB_NAME
            ).build()
        }

        @Provides
        fun provideResumeDao(resumeDb: ResumeDatabase): ResumeDao {
            return resumeDb.getResumeDao()
        }

    }

}