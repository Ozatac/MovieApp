package com.tunahanozatac.movieapp.domain.repository

import com.tunahanozatac.movieapp.domain.model.nowPlayingModel.MovieResponse
import com.tunahanozatac.movieapp.util.response.Resource

interface ListRepository {
    suspend fun getList(): Resource<MovieResponse>
}