package com.tunahanozatac.movieapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.tunahanozatac.movieapp.data.repository.UpComingRepositoryImp
import com.tunahanozatac.movieapp.domain.model.nowPlayingModel.MovieResponse
import com.tunahanozatac.movieapp.domain.usecase.GetListUseCase
import com.tunahanozatac.movieapp.domain.usecase.MoviesPagingSource
import com.tunahanozatac.movieapp.util.extensions.launchOnIO
import com.tunahanozatac.movieapp.util.response.Resource
import com.tunahanozatac.movieapp.util.response.UIStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val useCase: GetListUseCase,
    private val repository: UpComingRepositoryImp
) : ViewModel() {

    private val _uiState: MutableStateFlow<Resource<MovieResponse?>> =
        MutableStateFlow(Resource.Loading(UIStatus.LOADING))

    val moviesList = Pager(PagingConfig(1)) {
        MoviesPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

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
}