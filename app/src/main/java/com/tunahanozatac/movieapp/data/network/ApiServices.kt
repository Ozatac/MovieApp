package com.tunahanozatac.movieapp.data.network

import com.tunahanozatac.movieapp.domain.model.NowPlayingModel.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("movie/now_playing?page=1")
    suspend fun getNowPlayingMovies(): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int
    ): MovieResponse

//    // Note: This could be separated but since we don't have a lot of endpoints
//    // I've decided to put them into single file.
//    @GET("movie/{movieID}")
//    suspend fun getMovieDetails(
//        @Path("movieID") movieID: Int,
//    ): Response<MovieDetailsModel>
}