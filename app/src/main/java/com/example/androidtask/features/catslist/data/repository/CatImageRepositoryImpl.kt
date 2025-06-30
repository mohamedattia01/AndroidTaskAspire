package com.example.androidtask.features.catslist.data.repository

import com.example.androidtask.core.data.local.db.dao.CatImageDao
import com.example.androidtask.core.data.remote.NetworkResponse
import com.example.androidtask.features.catslist.data.remote.datasource.CatImageRemoteDataSource
import com.example.androidtask.features.catslist.domain.model.CatImageModel
import com.example.androidtask.features.catslist.domain.repositoryinterface.CatImageRepository
import javax.inject.Inject

class CatImageRepositoryImpl @Inject constructor(
    private val remoteDataSource: CatImageRemoteDataSource, private val catImageDao: CatImageDao
) : CatImageRepository {

    override suspend fun getCatImage(): NetworkResponse<List<CatImageModel>> {
        val response = remoteDataSource.getCatsImages()
        if (response is NetworkResponse.Success) {
            if (catImageDao.getAll().size >= 10) catImageDao.deleteAll()
            catImageDao.insertCatImages(response.data?.get(0)!!)
        }
        return response
    }

    override suspend fun getCatsImagesList(): List<CatImageModel> {
        var cats = emptyList<CatImageModel>()
        if (catImageDao.getAll().size == 10){
            cats = catImageDao.getAll()
            catImageDao.deleteAll()
        }
        return cats
    }
}