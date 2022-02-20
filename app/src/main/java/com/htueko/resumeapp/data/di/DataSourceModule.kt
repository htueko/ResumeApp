package com.htueko.resumeapp.data.di

import com.htueko.resumeapp.data.datasource.local.LocalDataSourceImpl
import com.htueko.resumeapp.data.mapper.LocalMapper
import com.htueko.resumeapp.domain.datasource.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    companion object {
        @Provides
        @Singleton
        fun provideLocalMapper(): LocalMapper {
            return LocalMapper()
        }
    }
}
