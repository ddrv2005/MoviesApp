package com.example.moviesapp.di

import com.example.moviesapp.viewmodels.MovieDetailViewModel
import com.example.moviesapp.viewmodels.PopularMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { PopularMoviesViewModel() }
    viewModel { MovieDetailViewModel() }
}