package com.example.androidtask.core.state

import com.example.androidtask.core.errors.ErrorModel

sealed class UiState<out T> {
    data object Loading: UiState<Nothing>()
    data class Success<T>(val data: T): UiState<T>()
    data class Error(val errorModel: ErrorModel): UiState<Nothing>()
}