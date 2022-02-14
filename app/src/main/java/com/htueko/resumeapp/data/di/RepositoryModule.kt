package com.htueko.resumeapp.data.di

import com.htueko.resumeapp.data.repository.local.LocalRepositoryImpl
import com.htueko.resumeapp.domain.repository.LocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideLocalRepository(resumeRepositoryImpl: LocalRepositoryImpl): LocalRepository

}