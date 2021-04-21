package com.example.moviesapp

import android.app.Application
import com.example.moviesapp.di.fragmentsModule
import com.example.moviesapp.di.networkModule
import com.example.moviesapp.di.repositoriesModule
import com.example.moviesapp.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MovieApplication)
            modules(
                networkModule,
                viewModelsModule,
                repositoriesModule,
                fragmentsModule
            )
        }
    }

}