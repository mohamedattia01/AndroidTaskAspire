package com.example.androidtask.core.errors

sealed class TaskErrors {
    object ConnectionError : TaskErrors()
    object TimeoutError : TaskErrors()
    object UnExpectedError : TaskErrors()
}