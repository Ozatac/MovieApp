package com.tunahanozatac.movieapp.data.repository

import com.tunahanozatac.movieapp.core.BaseRepository
import com.tunahanozatac.movieapp.data.network.ApiServices
import com.tunahanozatac.movieapp.domain.repository.UpComingRepository
import javax.inject.Inject

class UpComingRepositoryImp @Inject constructor(
    private val apiServices: ApiServices
) : BaseRepository(), UpComingRepository {
    suspend fun getPopularMoviesList(page: Int) = apiServices.getUpcomingMoviesNew(page)
}