package com.example.moviesapp.repositories

import com.example.moviesapp.models.Movie
import com.example.moviesapp.models.Response
import com.example.moviesapp.models.ResponseHandler
import com.example.moviesapp.network.MoviesApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.Exception

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

    suspend fun searchMovie(query: String): Response<List<Movie>> {
        return try {
            responseHandler.handleSuccess(moviesApi.searchMovie(query).results)
        } catch (exception: Exception) {
            responseHandler.handleException(exception)
        }
    }
}