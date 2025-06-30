package com.example.androidtask.features.catslist.presentation.viewmodel

import com.example.androidtask.core.errors.ErrorModel
import com.example.androidtask.core.state.UiState
import com.example.androidtask.features.catslist.domain.model.CatImageModel
import com.example.androidtask.features.catslist.domain.model.CatsImagesResult
import com.example.androidtask.features.catslist.domain.usecase.CatsImagesUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CatImageViewModelTest {

    @Mock
    private lateinit var catsImagesUseCase: CatsImagesUseCase
    private lateinit var catImageViewModel: CatImageViewModel

    @Before
    fun setup() {
        catImageViewModel = CatImageViewModel(catsImagesUseCase)
    }

    @Test
    fun handleWithGetCatsImagesList_shouldReturnCatsImages() = runTest {
        val catsImagesList =
            listOf(CatImageModel(id = "1", url = "https://mockedurl", width = "50", height = "100"))
        val expected = UiState.Success(catsImagesList)
        whenever(catsImagesUseCase.execute()).thenReturn(
            CatsImagesResult.CatsImagesSuccess(
                catsImagesList
            )
        )
        catImageViewModel.loadCatImagesList()
        advanceUntilIdle()
        catImageViewModel.catImagesStateFlow.emit(UiState.Success(catsImagesList))
        assertEquals(expected, catImageViewModel.catImagesStateFlow.value)
    }

    @Test
    fun handleWithGetCatsImagesList_shouldReturnFailure() = runTest {
        val errorModel = ErrorModel("", "")
        val expected = UiState.Error(errorModel)
        whenever(catsImagesUseCase.execute()).thenReturn(
            CatsImagesResult.CatsImagesFailure(
                errorModel
            )
        )
        catImageViewModel.loadCatImagesList()
        advanceUntilIdle()
        catImageViewModel.catImagesStateFlow.emit(UiState.Error(errorModel))
        assertEquals(expected, catImageViewModel.catImagesStateFlow.value)
    }
}