package com.example.moviesapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.models.Movie
import com.example.moviesapp.models.Status
import com.example.moviesapp.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchMoviesViewModel: ViewModel(), KoinComponent {

    private val repository: MoviesRepository by inject()
    private val _searchMovieActions: MutableLiveData<SearchMovieActions> = MutableLiveData()
    val searchMovieActions: LiveData<SearchMovieActions> = _searchMovieActions

    fun searchMovie(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _searchMovieActions.postValue(SearchMovieActions.OnLoading)
            val response = repository.searchMovie(query)
            when (response.status) {
                Status.SUCCESS -> {
                    response.data?.let {
                        _searchMovieActions.postValue(SearchMovieActions.OnMoviesRetrieved(it))
                    }
                }
                Status.ERROR -> _searchMovieActions.postValue(SearchMovieActions.OnError)
            }
        }
    }

}

sealed class SearchMovieActions {
    object OnLoading: SearchMovieActions()
    object OnError: SearchMovieActions()
    class OnMoviesRetrieved(val result: List<Movie>): SearchMovieActions()
}