package com.example.androidtask.features.catslist.domain.model

import com.example.androidtask.core.errors.ErrorModel

sealed class CatsImagesResult {
    data class CatsImagesSuccess(
        val catsImagesList: List<CatImageModel>
    ): CatsImagesResult()

    data class CatsImagesFailure(
        val errorModel: ErrorModel
    ): CatsImagesResult()
}