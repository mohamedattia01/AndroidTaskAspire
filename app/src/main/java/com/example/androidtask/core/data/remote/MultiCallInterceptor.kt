package com.example.androidtask.core.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class MultiCallInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        var response = chain.proceed(originalRequest)
        var count = 0

        while (originalRequest.url.toUrl().path.contains("images/search") && count < 10) {
            response.close()
            response = chain.proceed(originalRequest)
            count++
        }
        return response
    }
}