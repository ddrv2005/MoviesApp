package com.example.moviesapp.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val url = req.url.newBuilder().addQueryParameter("api_key", "06e2add617ac270d8e6a70d83bc2280e").build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}