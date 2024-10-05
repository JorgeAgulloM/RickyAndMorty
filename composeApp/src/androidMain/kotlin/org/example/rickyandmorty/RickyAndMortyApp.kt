package org.example.rickyandmorty

import android.app.Application
import org.example.rickyandmorty.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class RickyAndMortyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@RickyAndMortyApp)
        }
    }
}
