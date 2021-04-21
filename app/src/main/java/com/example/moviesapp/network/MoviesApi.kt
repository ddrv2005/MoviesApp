package com.example.moviesapp.network

import com.example.moviesapp.models.MovieDetail
import com.example.moviesapp.models.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(): MoviesResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): MovieDetail

    @GET("search/movie")
    suspend fun searchMovie(@Query("query") query: String): MoviesResponse
}