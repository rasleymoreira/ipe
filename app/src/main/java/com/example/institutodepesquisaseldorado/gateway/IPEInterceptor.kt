package com.example.institutodepesquisaseldorado.gateway

import com.example.institutodepesquisaseldorado.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class IPEInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer ${Constants.ACCESS_TOKEN_AUTH}")
            .build()

        return chain.proceed(request)
    }
}