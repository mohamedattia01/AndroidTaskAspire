package com.example.androidtask.core.data.local.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidtask.core.data.local.db.dao.CatImageDao
import com.example.androidtask.features.catslist.domain.model.CatImageModel

@Database(entities = [CatImageModel::class], version = 1)
abstract class CatImageDatabase : RoomDatabase() {
    abstract fun catImageDao(): CatImageDao
}