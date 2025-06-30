package com.example.androidtask.core.data.remote

import com.example.androidtask.core.errors.TaskErrors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

const val CONVERSION_SERVER_ERROR = 520
const val HTTP_TIME_OUT_CODE = 504

interface NetworkServiceCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T,
    ): NetworkResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                // invoke suspend service method
                val response = apiCall.invoke()

                // handle code range
                NetworkResponse.Success(
                    data = response,
                )
            } catch (throwable: Exception) {
                // check connection error
                if (throwable is TimeoutCancellationException) {
                    NetworkResponse.Failure(TaskErrors.TimeoutError, 0)
                } else if (throwable is IOException || (throwable is HttpException && throwable.code() == HTTP_TIME_OUT_CODE))
                    NetworkResponse.Failure(
                    TaskErrors.ConnectionError,
                    0
                )
                else {
                    // get http response
                    val statusResponse = (throwable as? HttpException)?.response()
                    // get response code fromm http exception
                    val httpCode = statusResponse?.code() ?: CONVERSION_SERVER_ERROR

                    NetworkResponse.Failure(
                        TaskErrors.UnExpectedError, httpCode.toLong()
                    )
                }
            }
        }
    }
}