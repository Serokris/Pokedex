package com.example.pokedex.di.component

import android.content.Context
import com.example.pokedex.di.module.*
import com.example.pokedex.presentation.favoritespokemonlist.FavoritesPokemonsListFragment
import com.example.pokedex.presentation.pokemonsearch.PokemonSearchFragment
import com.example.pokedex.presentation.showrandompokemon.ShowRandomPokemonFragment
import com.example.pokedex.di.common.ViewModelFactory
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
    fun inject(fragment: PokemonSearchFragment)
    fun inject(fragment: ShowRandomPokemonFragment)
    fun inject(fragment: FavoritesPokemonsListFragment)
    fun viewModelsFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}