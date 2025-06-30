package com.example.androidtask.features.catslist.domain.usecase

import com.example.androidtask.core.data.remote.NetworkResponse
import com.example.androidtask.core.domain.usecase.UseCase
import com.example.androidtask.core.errors.getModel
import com.example.androidtask.features.catslist.domain.model.CatsImagesResult
import com.example.androidtask.features.catslist.domain.repositoryinterface.CatImageRepository
import javax.inject.Inject

open class CatsImagesUseCase @Inject constructor(
    private val repository: CatImageRepository
) : UseCase<CatsImagesResult> {
    override suspend fun execute(): CatsImagesResult {
        return when (val data = repository.getCatImage()) {
            is NetworkResponse.Success -> CatsImagesResult.CatsImagesSuccess(repository.getCatsImagesList())
            is NetworkResponse.Failure -> CatsImagesResult.CatsImagesFailure(data.taskError.getModel())
        }
    }

}