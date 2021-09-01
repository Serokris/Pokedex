package com.example.pokedex.data.repository

import com.example.pokedex.data.source.local.dao.PokemonDao
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.data.source.remote.PokeApiService
import com.example.pokedex.domain.model.response.PokemonResponse
import com.example.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: PokeApiService,
    private val pokemonDao: PokemonDao
) : PokemonRepository {
    override suspend fun getPokemonByName(name: String): Response<PokemonResponse> {
        return apiService.getPokemonByName(name)
    }

    override suspend fun getPokemonById(id: Int): Response<PokemonResponse> {
        return apiService.getPokemonById(id)
    }

    override fun getAllFavoritePokemons(): Flow<List<Pokemon>> {
        return pokemonDao.getAllFavoritePokemons()
    }

    override suspend fun isAddedPokemonWithThisId(id: Int): Boolean? {
        return pokemonDao.isAddedPokemonWithThisId(id)
    }

    override suspend fun insert(pokemon: Pokemon) {
        pokemonDao.insert(pokemon)
    }

    override suspend fun delete(pokemon: Pokemon) {
        pokemonDao.delete(pokemon)
    }

    override suspend fun deleteAllFavoritePokemons() {
        pokemonDao.deleteAllFavoritePokemons()
    }
}