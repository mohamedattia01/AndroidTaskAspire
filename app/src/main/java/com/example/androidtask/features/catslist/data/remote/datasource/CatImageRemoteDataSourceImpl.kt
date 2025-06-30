package com.example.androidtask.features.catslist.data.remote.datasource

import com.example.androidtask.core.data.remote.NetworkResponse
import com.example.androidtask.core.data.remote.NetworkServiceCall
import com.example.androidtask.features.catslist.data.remote.apiservices.CatImageApiService
import com.example.androidtask.features.catslist.domain.model.CatImageModel
import javax.inject.Inject

class CatImageRemoteDataSourceImpl @Inject constructor(
    private val catImageService: CatImageApiService
) : CatImageRemoteDataSource, NetworkServiceCall {

    override suspend fun getCatsImages(): NetworkResponse<List<CatImageModel>> {
        return safeApiCall { catImageService.getCatImage() }
    }
}