package com.example.domain.interactor

import com.example.domain.common.Errors
import com.example.domain.common.Result
import com.example.domain.model.Pokemon
import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class PokemonInteractor @Inject constructor(private val pokemonRepository: PokemonRepository) {

    fun getPokemonByName(name: String): Flow<Result<Pokemon>> = flow {
        try {
            emit(Result.Loading())
            val pokemonResponse = pokemonRepository.getPokemonByName(name)
            emit(Result.Success(pokemonResponse))
        } catch (e: IOException) {
            emit(Result.Error<Pokemon>(Errors.FAILED_TO_CONNECT_TO_SERVER))
        } catch (e: Exception) {
            emit(Result.Error<Pokemon>(Errors.FAILED_TO_FIND_POKEMON))
        }
    }

    fun getPokemonById(id: Int): Flow<Result<Pokemon>> = flow {
        try {
            emit(Result.Loading())
            val pokemonResponse = pokemonRepository.getPokemonById(id)
            emit(Result.Success(pokemonResponse))
        } catch (e: IOException) {
            emit(Result.Error<Pokemon>(Errors.FAILED_TO_CONNECT_TO_SERVER))
        } catch (e: Exception) {
            emit(Result.Error<Pokemon>(Errors.FAILED_TO_FIND_POKEMON))
        }
    }

    fun getAllFavoritePokemons(): Flow<List<Pokemon>> = pokemonRepository.getAllFavoritePokemons()

    fun isAddedPokemonWithThisId(id: Int): Flow<Boolean> =
        pokemonRepository.isAddedPokemonWithThisId(id)

    suspend fun insert(pokemon: Pokemon) = pokemonRepository.insert(pokemon)

    suspend fun delete(pokemon: Pokemon) = pokemonRepository.delete(pokemon)

    suspend fun deleteAllFavoritePokemons() = pokemonRepository.deleteAllFavoritePokemons()
}