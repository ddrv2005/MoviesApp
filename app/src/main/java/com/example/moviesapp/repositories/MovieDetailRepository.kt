package com.example.moviesapp.repositories

import com.example.moviesapp.models.MovieDetail
import com.example.moviesapp.models.Response
import com.example.moviesapp.models.ResponseHandler
import com.example.moviesapp.network.MoviesApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.lang.Exception

class MovieDetailRepository(val responseHandler: ResponseHandler): KoinComponent {

    private val moviesApi: MoviesApi by inject()

    suspend fun getMovieDetail(movieId: Int): Response<MovieDetail> {
        return try {
            responseHandler.handleSuccess(moviesApi.getMovieDetails(movieId))
        } catch (exception: Exception) {
            responseHandler.handleException(exception)
        }
    }
}