package com.tunahanozatac.movieapp.data.repository

import com.tunahanozatac.movieapp.core.BaseRepository
import com.tunahanozatac.movieapp.data.network.ApiServices
import com.tunahanozatac.movieapp.domain.model.DetailsModel.MovieDetailsModel
import com.tunahanozatac.movieapp.domain.repository.DetailsRepository
import com.tunahanozatac.movieapp.util.response.Resource
import javax.inject.Inject

class DetailsRepositoryImp @Inject constructor(
    private val apiServices: ApiServices
) : BaseRepository(), DetailsRepository {
    override suspend fun detailsMoview(id: Int): Resource<MovieDetailsModel> {
        return safeApiRequest {
            apiServices.getMovieDetails(movieID = id)
        }
    }
}