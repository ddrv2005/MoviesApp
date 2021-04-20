package com.example.moviesapp.repositories

import com.example.moviesapp.models.Movie
import com.example.moviesapp.models.Response
import com.example.moviesapp.models.ResponseHandler
import com.example.moviesapp.network.MoviesApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.lang.Exception

class MoviesRepository(val responseHandler: ResponseHandler): KoinComponent {

    private val moviesApi: MoviesApi by inject()

    suspend fun getPopularMovies(): Response<List<Movie>> {
        return try {
            responseHandler.handleSuccess(moviesApi.getPopularMovies()
                .results)
        } catch (exception: Exception) {
            responseHandler.handleException(exception)
        }
    }
}