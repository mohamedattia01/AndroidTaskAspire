package com.example.androidtask.core.data.remote

import com.example.androidtask.core.errors.TaskErrors

sealed class NetworkResponse<T> {
    data class Success<T>(
        val data: T?,
    ) : NetworkResponse<T>()

    data class Failure<T>(
        val taskError: TaskErrors,
        val httpCode: Long,
    ) : NetworkResponse<T>()
}