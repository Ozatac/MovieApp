package com.tunahanozatac.movieapp.domain.usecase

import com.tunahanozatac.movieapp.domain.model.nowPlayingModel.MovieResponse
import com.tunahanozatac.movieapp.domain.repository.ListRepository
import com.tunahanozatac.movieapp.util.response.Resource
import javax.inject.Inject

class GetListUseCase @Inject constructor(
    private val repository: ListRepository
) {
    suspend operator fun invoke(): Resource<MovieResponse> {
        return repository.getList()
    }
}