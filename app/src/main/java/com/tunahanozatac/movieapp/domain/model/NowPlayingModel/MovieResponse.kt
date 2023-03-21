package com.tunahanozatac.movieapp.domain.model.NowPlayingModel

import com.google.gson.annotations.SerializedName
import com.tunahanozatac.movieapp.domain.model.NowPlayingModel.MovieModel

data class MovieResponse(
    val page: Int,
    val results: List<MovieModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)