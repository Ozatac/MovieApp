package com.tunahanozatac.movieapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahanozatac.movieapp.domain.model.NowPlayingModel.MovieResponse
import com.tunahanozatac.movieapp.domain.usecase.GetListUseCase
import com.tunahanozatac.movieapp.domain.usecase.UpComingUseCase
import com.tunahanozatac.movieapp.util.launchOnIO
import com.tunahanozatac.movieapp.util.response.Resource
import com.tunahanozatac.movieapp.util.response.UIStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor() : ViewModel() {

}