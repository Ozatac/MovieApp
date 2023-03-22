package com.tunahanozatac.movieapp.data.network

import com.tunahanozatac.movieapp.domain.model.DetailsModel.MovieDetailsModel
import com.tunahanozatac.movieapp.domain.model.NowPlayingModel.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("movie/now_playing?page=1")
    suspend fun getNowPlayingMovies(): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMoviesNew(
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("movie/{movieID}")
    suspend fun getMovieDetails(
        @Path("movieID") movieID: Int,
    ): MovieDetailsModel
}