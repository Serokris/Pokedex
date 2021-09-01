package com.example.pokedex.domain.repository

import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.model.response.PokemonResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface PokemonRepository {
    suspend fun getPokemonByName(name: String): Response<PokemonResponse>

    suspend fun getPokemonById(id: Int): Response<PokemonResponse>

    fun getAllFavoritePokemons(): Flow<List<Pokemon>>

    suspend fun isAddedPokemonWithThisId(id: Int): Boolean?

    suspend fun insert(pokemon: Pokemon)

    suspend fun delete(pokemon: Pokemon)

    suspend fun deleteAllFavoritePokemons()
}