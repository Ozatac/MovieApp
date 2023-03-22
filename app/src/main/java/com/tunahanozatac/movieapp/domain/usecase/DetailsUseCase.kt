package com.tunahanozatac.movieapp.domain.usecase

import com.tunahanozatac.movieapp.domain.model.detailsModel.MovieDetailsModel
import com.tunahanozatac.movieapp.domain.repository.DetailsRepository
import com.tunahanozatac.movieapp.util.response.Resource
import javax.inject.Inject

class DetailsUseCase @Inject constructor(
    private val repository: DetailsRepository
) {
    suspend operator fun invoke(id: Int): Resource<MovieDetailsModel> {
        return repository.detailsMovie(id = id)
    }
}