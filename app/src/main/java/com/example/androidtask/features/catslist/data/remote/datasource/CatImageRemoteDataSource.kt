package com.example.androidtask.features.catslist.data.remote.datasource

import com.example.androidtask.core.data.remote.NetworkResponse
import com.example.androidtask.features.catslist.domain.model.CatImageModel

interface CatImageRemoteDataSource {
    suspend fun getCatsImages(): NetworkResponse<List<CatImageModel>>
}