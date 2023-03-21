package com.tunahanozatac.movieapp.domain.usecase

import com.tunahanozatac.movieapp.domain.model.NowPlayingModel.MovieResponse
import com.tunahanozatac.movieapp.domain.repository.UpComingRepository
import com.tunahanozatac.movieapp.util.response.Resource
import javax.inject.Inject

class UpComingUseCase @Inject constructor(
    private val repository: UpComingRepository
) {
    suspend operator fun invoke(page : Int): Resource<MovieResponse> {
        return repository.getUpComing(page)
    }
}