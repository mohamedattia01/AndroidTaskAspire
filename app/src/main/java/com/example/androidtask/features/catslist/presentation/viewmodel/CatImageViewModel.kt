package com.example.androidtask.features.catslist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtask.core.state.UiState
import com.example.androidtask.features.catslist.domain.model.CatImageModel
import com.example.androidtask.features.catslist.domain.model.CatsImagesResult
import com.example.androidtask.features.catslist.domain.usecase.CatsImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatImageViewModel @Inject constructor(
    private val catImageUseCase: CatsImagesUseCase
) : ViewModel() {

    val catImagesStateFlow = MutableStateFlow<UiState<List<CatImageModel>>>(UiState.Loading)

    fun loadCatImagesList() {
        viewModelScope.launch(Dispatchers.IO) {
            catImagesStateFlow.emit(UiState.Loading)

            when (val result = catImageUseCase.execute()) {
                is CatsImagesResult.CatsImagesFailure -> catImagesStateFlow.emit(
                    UiState.Error(
                        result.errorModel
                    )
                )

                is CatsImagesResult.CatsImagesSuccess -> catImagesStateFlow.emit(
                    UiState.Success(
                        result.catsImagesList
                    )
                )
            }
        }
    }
}