package com.example.pokedex.di.module

import androidx.lifecycle.ViewModel
import dagger.Module
import com.example.pokedex.presentation.viewmodel.PokemonViewModel
import com.example.pokedex.presentation.viewmodel.ViewModelKey
import dagger.Binds
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PokemonViewModel::class)
    abstract fun providePokemonViewModel(viewModel: PokemonViewModel): ViewModel
}