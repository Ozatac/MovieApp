package com.tunahanozatac.movieapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahanozatac.movieapp.domain.model.detailsModel.MovieDetailsModel
import com.tunahanozatac.movieapp.domain.usecase.DetailsUseCase
import com.tunahanozatac.movieapp.util.extensions.launchOnIO
import com.tunahanozatac.movieapp.util.response.Resource
import com.tunahanozatac.movieapp.util.response.UIStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCase: DetailsUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<Resource<MovieDetailsModel?>> =
        MutableStateFlow(Resource.Loading(UIStatus.LOADING))
    val uiState: StateFlow<Resource<MovieDetailsModel?>> get() = _uiState

    fun getDetails(id: Int): StateFlow<Resource<MovieDetailsModel?>> {
        viewModelScope.launchOnIO {
            when (val response = useCase.invoke(id = id)) {
                is Resource.Success -> {
                    _uiState.emit(Resource.Success(response.data, response.state))
                }
                is Resource.Error -> {
                    _uiState.emit(
                        Resource.Error(
                            "CheckYourInternetConnection", response.state
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