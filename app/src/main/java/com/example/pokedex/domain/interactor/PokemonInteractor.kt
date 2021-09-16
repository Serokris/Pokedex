package com.example.pokedex.domain.interactor

import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.model.response.PokemonResponse
import com.example.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class PokemonInteractor @Inject constructor (private val pokemonRepository: PokemonRepository) {
    suspend fun getPokemonByName(name: String): Response<PokemonResponse> {
        return pokemonRepository.getPokemonByName(name)
    }

    suspend fun getPokemonById(id: Int): Response<PokemonResponse> {
        return pokemonRepository.getPokemonById(id)
    }

    fun getAllFavoritePokemons(): Flow<List<Pokemon>> {
        return pokemonRepository.getAllFavoritePokemons()
    }

    fun isAddedPokemonWithThisId(id: Int): Flow<Boolean> {
        return pokemonRepository.isAddedPokemonWithThisId(id)
    }

    suspend fun insert(pokemon: Pokemon) = pokemonRepository.insert(pokemon)

    suspend fun delete(pokemon: Pokemon) = pokemonRepository.delete(pokemon)

    suspend fun deleteAllFavoritePokemons() = pokemonRepository.deleteAllFavoritePokemons()
}