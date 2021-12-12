package com.example.pokedex

import android.app.Application
import com.example.pokedex.di.component.AppComponent
import com.example.pokedex.di.component.DaggerAppComponent

class PokedexApplication : Application() {

    val appComponent: AppComponent by lazy(LazyThreadSafetyMode.NONE) {
        DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}