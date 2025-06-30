package com.example.androidtask.features.catslist.di

import com.example.androidtask.features.catslist.data.remote.apiservices.CatImageApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class CatImageApiModule {

    @Provides
    fun provideCatImageService(retrofit: Retrofit) = retrofit.create(CatImageApiService::class.java)
}