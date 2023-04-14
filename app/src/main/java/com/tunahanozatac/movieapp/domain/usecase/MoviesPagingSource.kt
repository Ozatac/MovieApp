package com.tunahanozatac.movieapp.domain.usecase

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tunahanozatac.movieapp.data.repository.UpComingRepositoryImp
import com.tunahanozatac.movieapp.domain.model.nowPlayingModel.MovieModel
import retrofit2.HttpException
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val apiServices: UpComingRepositoryImp
) : PagingSource<Int, MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiServices.getPopularMoviesList(currentPage)
            val data = response.body()?.results
            val responseData = mutableListOf<MovieModel>()
            data?.let { responseData.addAll(it) }

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return null
    }
}