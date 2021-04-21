package com.example.moviesapp.di

import com.example.moviesapp.fragments.MovieDetailFragment
import com.example.moviesapp.fragments.MoviesFragment
import org.koin.dsl.module

val fragmentsModule = module {
    single { MoviesFragment() }
    single { MovieDetailFragment() }
}