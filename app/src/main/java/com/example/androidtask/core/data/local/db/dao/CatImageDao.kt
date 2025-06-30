package com.example.androidtask.core.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidtask.features.catslist.domain.model.CatImageModel

@Dao
interface CatImageDao {

    @Query("SELECT * FROM catimagemodel")
    fun getAll(): List<CatImageModel>

    @Query("DELETE FROM catimagemodel")
    fun deleteAll()

    @Insert
    fun insertCatImages(vararg catImages: CatImageModel)
}