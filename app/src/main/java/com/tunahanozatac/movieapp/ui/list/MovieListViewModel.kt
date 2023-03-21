package com.tunahanozatac.movieapp.ui.list

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
class MovieListViewModel @Inject constructor(
    private val useCase: GetListUseCase,
    private val upComingUseCase: UpComingUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<Resource<MovieResponse?>> =
        MutableStateFlow(Resource.Loading(UIStatus.LOADING))
    val uiState: StateFlow<Resource<MovieResponse?>> get() = _uiState

    private val _uiStateUpComing: MutableStateFlow<Resource<MovieResponse?>> =
        MutableStateFlow(Resource.Loading(UIStatus.LOADING))
    val uiStateUpComing: StateFlow<Resource<MovieResponse?>> get() = _uiStateUpComing

    fun getList(): StateFlow<Resource<MovieResponse?>> {
        viewModelScope.launchOnIO {
            when (val response = useCase.invoke()) {
                is Resource.Success -> {
                    _uiState.emit(
                        Resource.Success(response.data, response.state)
                    )
                }
                is Resource.Error -> {
                    _uiState.emit(
                        Resource.Error(
                            "R.string.CheckYourInternetConnection", response.state
                        )
                    )
                }
                is Resource.Loading -> {
                    _uiState.emit(Resource.Loading(UIStatus.LOADING))
                }
            }
        }
        return _uiState
    }

    fun getUpComing(): StateFlow<Resource<MovieResponse?>> {
        viewModelScope.launchOnIO {
            when (val response = upComingUseCase.invoke(1)) {
                is Resource.Success -> {
                    _uiStateUpComing.emit(
                        Resource.Success(response.data, response.state)
                    )
                }
                is Resource.Error -> {
                    _uiStateUpComing.emit(
                        Resource.Error(
                            "R.string.CheckYourInternetConnection", response.state
                        )
                    )
                }
                is Resource.Loading -> {
                    _uiStateUpComing.emit(Resource.Loading(UIStatus.LOADING))
                }
            }
        }
        return _uiStateUpComing
    }
}