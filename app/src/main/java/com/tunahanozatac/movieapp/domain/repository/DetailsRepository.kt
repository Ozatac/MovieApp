package com.tunahanozatac.movieapp.domain.repository

import com.tunahanozatac.movieapp.domain.model.detailsModel.MovieDetailsModel
import com.tunahanozatac.movieapp.util.response.Resource

interface DetailsRepository {
    suspend fun detailsMovie(id: Int): Resource<MovieDetailsModel>
}