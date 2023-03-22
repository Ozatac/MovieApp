package com.tunahanozatac.movieapp.domain.usecase

import com.tunahanozatac.movieapp.domain.repository.UpComingRepository
import javax.inject.Inject

class UpComingUseCase @Inject constructor(
    private val repository: UpComingRepository
)