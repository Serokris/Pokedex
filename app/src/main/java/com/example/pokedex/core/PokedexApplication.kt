package com.example.pokedex.core

import android.app.Application
import com.example.pokedex.di.component.AppComponent
import com.example.pokedex.di.component.DaggerAppComponent
import com.example.pokedex.di.module.ContextModule

class PokedexApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }
}