package com.example.domain.repository

import com.example.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemonByName(name: String): Pokemon

    suspend fun getPokemonById(id: Int): Pokemon

    fun getAllFavoritePokemons(): Flow<List<Pokemon>>

    fun isAddedPokemonWithThisId(id: Int): Flow<Boolean>

    suspend fun insert(pokemon: Pokemon)

    suspend fun delete(pokemon: Pokemon)

    suspend fun deleteAllFavoritePokemons()
}