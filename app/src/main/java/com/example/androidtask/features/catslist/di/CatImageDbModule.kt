package com.example.androidtask.features.catslist.di

import android.content.Context
import androidx.room.Room
import com.example.androidtask.core.data.local.db.database.CatImageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CatImageDbModule {
    @Singleton
    @Provides
    fun provideUserDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app, CatImageDatabase::class.java, "cat-image-database"
    ).build()

    @Singleton
    @Provides
    fun provideUserDao(db: CatImageDatabase) = db.catImageDao()
}