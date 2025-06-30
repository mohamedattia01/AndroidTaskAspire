package com.example.androidtask.features.catslist.di

import com.example.androidtask.features.catslist.data.remote.datasource.CatImageRemoteDataSource
import com.example.androidtask.features.catslist.data.remote.datasource.CatImageRemoteDataSourceImpl
import com.example.androidtask.features.catslist.data.repository.CatImageRepositoryImpl
import com.example.androidtask.features.catslist.domain.repositoryinterface.CatImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class CatImageAbstractModule {
    @Binds
    abstract fun bindRepository(repository: CatImageRepositoryImpl): CatImageRepository

    @Binds
    abstract fun bindDataSource(dataSource: CatImageRemoteDataSourceImpl): CatImageRemoteDataSource
}