package com.example.androidtask.features.catslist.domain.repositoryinterface

import com.example.androidtask.core.data.remote.NetworkResponse
import com.example.androidtask.features.catslist.domain.model.CatImageModel

interface CatImageRepository {
    suspend fun getCatImage(): NetworkResponse<List<CatImageModel>>
    suspend fun getCatsImagesList(): List<CatImageModel>
}