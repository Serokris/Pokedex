package com.example.pokedex.di.module

import com.example.data.repository.PokemonRepositoryImpl
import com.example.data.source.local.dao.PokemonDao
import com.example.data.source.remote.PokeApiService
import com.example.domain.repository.PokemonRepository
import com.example.domain.interactor.PokemonInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataModule {
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