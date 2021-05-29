package com.example.tmdbmvvm

import android.app.Application
import com.example.tmdbmvvm.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            androidLogger(Level.ERROR)
            modules(listOf(homeModule))
        }
    }
}