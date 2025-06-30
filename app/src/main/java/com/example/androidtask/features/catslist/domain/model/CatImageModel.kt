package com.example.androidtask.features.catslist.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CatImageModel(
    @SerializedName("id")
    @PrimaryKey
    val id: String,
    @SerializedName("url")
    @ColumnInfo(name = "url")
    val url: String,
    @SerializedName("width")
    @ColumnInfo(name = "width")
    val width: String,
    @SerializedName("height")
    @ColumnInfo(name = "height")
    val height: String
)
