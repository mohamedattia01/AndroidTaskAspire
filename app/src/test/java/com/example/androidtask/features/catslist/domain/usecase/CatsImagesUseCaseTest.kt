package com.example.androidtask.features.catslist.domain.usecase

import com.example.androidtask.core.data.remote.NetworkResponse
import com.example.androidtask.core.errors.TaskErrors
import com.example.androidtask.features.catslist.domain.model.CatImageModel
import com.example.androidtask.features.catslist.domain.repositoryinterface.CatImageRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CatsImagesUseCaseTest {
    @Mock
    lateinit var repository: CatImageRepository

    private lateinit var catsImagesUseCase: CatsImagesUseCase

    @Before
    fun setup() {
        catsImagesUseCase = CatsImagesUseCase(repository)
    }

    @Test
    fun test_execute_use_case_when_get_cats_api_success(): Unit =
        runTest {

            val catsImagesList =
            listOf(CatImageModel(id = "1", url = "https://mockedurl", width = "50", height = "100"))

            val successResponse = NetworkResponse.Success(catsImagesList)

            Mockito.`when`(repository.getCatImage())
                .thenReturn(
                    successResponse
                )

            Mockito.`when`(repository.getCatsImagesList())
                .thenReturn(
                    catsImagesList
                )
            catsImagesUseCase.execute()
            Mockito.verify(repository, Mockito.times(1)).getCatImage()
            Mockito.verify(repository, Mockito.times(1)).getCatsImagesList()

        }

    @Test
    fun test_execute_use_case_when_get_cats_api_failure(): Unit =
        runTest {
            val failureResponse = NetworkResponse.Failure<List<CatImageModel>>(TaskErrors.ConnectionError, 0)

            Mockito.`when`(repository.getCatImage())
                .thenReturn(
                    failureResponse
                )
            catsImagesUseCase.execute()
            Mockito.verify(repository, Mockito.times(1)).getCatImage()
        }
}