package com.tunahanozatac.movieapp.data.repository

import com.tunahanozatac.movieapp.core.BaseRepository
import com.tunahanozatac.movieapp.data.network.ApiServices
import com.tunahanozatac.movieapp.domain.model.NowPlayingModel.MovieResponse
import com.tunahanozatac.movieapp.domain.repository.UpComingRepository
import com.tunahanozatac.movieapp.util.response.Resource
import javax.inject.Inject

class UpComingRepositoryImp @Inject constructor(
    private val apiServices: ApiServices
) : BaseRepository(), UpComingRepository {

    override suspend fun getUpComing(page: Int): Resource<MovieResponse> {
        return safeApiRequest {
            apiServices.getUpcomingMovies(page)
        }
    }
}