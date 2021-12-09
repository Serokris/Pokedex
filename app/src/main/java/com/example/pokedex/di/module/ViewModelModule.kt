package com.example.pokedex.di.module

import androidx.lifecycle.ViewModel
import dagger.Module
import com.example.pokedex.di.common.ViewModelKey
import com.example.pokedex.presentation.favoritespokemonlist.FavoritesPokemonListViewModel
import com.example.pokedex.presentation.pokemonsearch.PokemonSearchViewModel
import com.example.pokedex.presentation.showrandompokemon.ShowRandomPokemonViewModel
import dagger.Binds
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PokemonSearchViewModel::class)
    abstract fun bindPokemonSearchViewModel(viewModel: PokemonSearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShowRandomPokemonViewModel::class)
    abstract fun bindShowRandomPokemonViewModel(viewModel: ShowRandomPokemonViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesPokemonListViewModel::class)
    abstract fun bindFavoritesPokemonListViewModel(viewModel: FavoritesPokemonListViewModel): ViewModel
}