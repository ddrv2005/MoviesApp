package com.example.moviesapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.models.MovieDetail
import com.example.moviesapp.models.Status
import com.example.moviesapp.repositories.MovieDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MovieDetailViewModel: ViewModel(), KoinComponent {

    private val repository: MovieDetailRepository by inject()
    private var _movieDetailActions: MutableLiveData<MovieDetailActions> = MutableLiveData()
    val movieDetailActions: LiveData<MovieDetailActions> = _movieDetailActions

    fun requestPopularMovies(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _movieDetailActions.postValue(MovieDetailActions.OnLoading)
            val response = repository.getMovieDetail(movieId)
            when (response.status) {
                Status.SUCCESS -> {
                    response.data?.let {
                        _movieDetailActions.postValue(MovieDetailActions.OnMovieDetailRetrieved(it))
                    }
                }
                Status.ERROR -> _movieDetailActions.postValue(MovieDetailActions.OnError)
            }
        }
    }
}

sealed class MovieDetailActions {
    object OnError: MovieDetailActions()
    object OnLoading: MovieDetailActions()
    class OnMovieDetailRetrieved(val movieDetail: MovieDetail): MovieDetailActions()
}