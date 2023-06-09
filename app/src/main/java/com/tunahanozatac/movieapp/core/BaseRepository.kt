package com.tunahanozatac.movieapp.core

import com.tunahanozatac.movieapp.util.response.Resource
import com.tunahanozatac.movieapp.util.response.UIStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {
    suspend fun <T> safeApiRequest(apiRequest: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiRequest.invoke(), UIStatus.SUCCESS)
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Error("Server Error", UIStatus.ERROR)
                    }

                    else -> {
                        Resource.Error("Network Error", UIStatus.ERROR)
                    }
                }
            }
        }
    }
}