package com.tunahanozatac.movieapp.data.network

import com.tunahanozatac.movieapp.domain.model.detailsModel.MovieDetailsModel
import com.tunahanozatac.movieapp.domain.model.nowPlayingModel.MovieResponse
import com.tunahanozatac.movieapp.util.Constants.MovieDetails
import com.tunahanozatac.movieapp.util.Constants.NowPlayingMovies
import com.tunahanozatac.movieapp.util.Constants.UpcomingMoviesNew
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET(NowPlayingMovies)
    suspend fun getNowPlayingMovies(): MovieResponse

    @GET(UpcomingMoviesNew)
    suspend fun getUpcomingMoviesNew(
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET(MovieDetails)
    suspend fun getMovieDetails(
        @Path("movieID") movieID: Int,
    ): MovieDetailsModel
}