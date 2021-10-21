package com.example.pokedex

import android.app.Application
import com.example.pokedex.di.component.AppComponent
import com.example.pokedex.di.component.DaggerAppComponent

class PokedexApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}