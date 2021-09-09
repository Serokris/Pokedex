package com.example.pokedex.di

import com.example.pokedex.data.repository.PokemonRepositoryImpl
import com.example.pokedex.data.source.local.dao.PokemonDao
import com.example.pokedex.data.source.remote.PokeApiService
import com.example.pokedex.domain.repository.PokemonRepository
import com.example.pokedex.domain.interactor.PokemonInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Singleton
    @Provides
    fun providePokemonRepository(
        pokemonApiService: PokeApiService,
        pokemonDao: PokemonDao
    ) : PokemonRepository {
        return PokemonRepositoryImpl(pokemonApiService, pokemonDao)
    }

    @Singleton
    @Provides
    fun providePokemonInteractor(pokemonRepository: PokemonRepository): PokemonInteractor {
        return PokemonInteractor(pokemonRepository)
    }
}