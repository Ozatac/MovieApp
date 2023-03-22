package com.tunahanozatac.movieapp.domain.repository

import com.tunahanozatac.movieapp.domain.model.DetailsModel.MovieDetailsModel
import com.tunahanozatac.movieapp.util.response.Resource

interface DetailsRepository {
    suspend fun detailsMoview(id: Int): Resource<MovieDetailsModel>
}