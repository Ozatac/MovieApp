package com.tunahanozatac.movieapp.util

import com.tunahanozatac.movieapp.BuildConfig

object Constants {
    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val TMDB_IMAGE_URL = "https://image.tmdb.org/t/p/original"
    const val IMDB_REDIRECT_URL =
        "https://www.themoviedb.org/redirect?external_source=imdb_id&external_id="
    const val NowPlayingMovies = "movie/now_playing?page=1"
    const val UpcomingMoviesNew = "movie/upcoming"
    const val MovieDetails = "movie/{movieID}"
}