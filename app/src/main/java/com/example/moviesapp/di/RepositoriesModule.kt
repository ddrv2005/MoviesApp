package com.example.moviesapp.di

import com.example.moviesapp.models.ResponseHandler
import com.example.moviesapp.repositories.MoviesRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single { MoviesRepository(ResponseHandler()) }
}