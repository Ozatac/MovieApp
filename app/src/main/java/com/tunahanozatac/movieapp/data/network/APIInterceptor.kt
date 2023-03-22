package com.tunahanozatac.movieapp.data.network

import com.tunahanozatac.movieapp.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class APIInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("api_key", Constants.API_KEY).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}