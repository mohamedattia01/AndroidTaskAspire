package com.example.androidtask.core.domain.usecase

interface UseCase<O> {
    suspend fun execute(): O
}