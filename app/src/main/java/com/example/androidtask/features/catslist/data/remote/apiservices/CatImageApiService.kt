package com.example.androidtask.features.catslist.data.remote.apiservices

import com.example.androidtask.features.catslist.domain.model.CatImageModel
import retrofit2.http.GET
import retrofit2.http.Headers

interface CatImageApiService {
    @GET("images/search")
    @Headers("Content-Type:application/json")
    suspend fun getCatImage(): List<CatImageModel>
}