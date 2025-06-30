package com.example.androidtask.core.errors

data class ErrorModel(
    var title: String,
    var description: String,
    var errorType: TaskErrors = TaskErrors.UnExpectedError
) : Throwable()

fun TaskErrors.getModel(): ErrorModel {
    return when (this) {
        is TaskErrors.UnExpectedError -> ErrorModel(
            title = "Unexpected Error",
            description = "Unexpected error happened",
            errorType = TaskErrors.UnExpectedError
        )

        is TaskErrors.ConnectionError -> ErrorModel(
            title = "Connection Error",
            description = "Connection error",
            errorType = TaskErrors.ConnectionError
        )

        is TaskErrors.TimeoutError -> ErrorModel(
            title = "Timeout Error",
            description = "Timeout error",
            errorType = TaskErrors.TimeoutError
        )
    }
}