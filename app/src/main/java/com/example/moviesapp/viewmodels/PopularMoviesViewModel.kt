package com.example.moviesapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.models.Movie
import com.example.moviesapp.models.Response
import com.example.moviesapp.models.Status
import com.example.moviesapp.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PopularMoviesViewModel: ViewModel(), KoinComponent {

    private val repository: MoviesRepository by inject()
    private var _popularMoviesActions: MutableLiveData<PopularMoviesActions> = MutableLiveData()
    val popularMoviesActions: LiveData<PopularMoviesActions> = _popularMoviesActions

    fun requestPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _popularMoviesActions.postValue(PopularMoviesActions.OnLoading)
            val response = repository.getPopularMovies()
            when (response.status) {
                Status.SUCCESS -> {
                    response.data?.let {
                        _popularMoviesActions.postValue(PopularMoviesActions.OnMoviesRetrieved(it))
                    }
                }
                Status.ERROR -> _popularMoviesActions.postValue(PopularMoviesActions.OnError)
            }
        }
    }
}

sealed class PopularMoviesActions {
    object OnError: PopularMoviesActions()
    object OnLoading: PopularMoviesActions()
    class OnMoviesRetrieved(val movies: List<Movie>): PopularMoviesActions()
}