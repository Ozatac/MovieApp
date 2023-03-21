package com.tunahanozatac.movieapp.data.repository

import com.tunahanozatac.movieapp.domain.model.NowPlayingModel.MovieResponse
import com.tunahanozatac.movieapp.domain.repository.ListRepository
import com.tunahanozatac.movieapp.core.BaseRepository
import com.tunahanozatac.movieapp.util.response.Resource
import com.tunahanozatac.movieapp.data.network.ApiServices
import javax.inject.Inject

class MovieNowPlayingRepositoryImp @Inject constructor(
    private val apiServices: ApiServices
) : BaseRepository(), ListRepository {

    override suspend fun getList(): Resource<MovieResponse> {
        return safeApiRequest {
            apiServices.getNowPlayingMovies()
        }
    }
}