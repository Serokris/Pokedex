package com.example.pokedex.di.component

import android.content.Context
import com.example.pokedex.di.common.ViewModelFactory
import com.example.pokedex.di.module.DataModule
import com.example.pokedex.di.module.DatabaseModule
import com.example.pokedex.di.module.NetworkModule
import com.example.pokedex.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        DatabaseModule::class,
        DataModule::class,
        NetworkModule::class,
        ViewModelModule::class
    ]
)
@Singleton
interface AppComponent {
    fun viewModelsFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}