package com.example.moviesapp.network

import com.example.moviesapp.models.MoviesResponse
import retrofit2.http.GET

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(): MoviesResponse
}