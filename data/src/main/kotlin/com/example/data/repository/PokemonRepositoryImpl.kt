package com.example.data.repository

import com.example.data.mappers.toPokemon
import com.example.data.mappers.toPokemonEntity
import com.example.data.source.local.dao.PokemonDao
import com.example.data.source.remote.PokeApiService
import com.example.domain.model.Pokemon
import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: PokeApiService,
    private val pokemonDao: PokemonDao
) : PokemonRepository {

    override suspend fun getPokemonByName(name: String): Pokemon {
        return apiService.getPokemonByName(name).toPokemon()
    }

    override suspend fun getPokemonById(id: Int): Pokemon {
        return apiService.getPokemonById(id).toPokemon()
    }

    override fun getAllFavoritePokemons(): Flow<List<Pokemon>> {
        return pokemonDao.getAllFavoritePokemons()
            .map { pokemonEntityList ->
                pokemonEntityList.map { pokemonEntity -> pokemonEntity.toPokemon() }
            }
    }

    override fun isAddedPokemonWithThisId(id: Int): Flow<Boolean> {
        return pokemonDao.isAddedPokemonWithThisId(id)
    }

    override suspend fun insert(pokemon: Pokemon) {
        pokemonDao.insert(pokemon.toPokemonEntity())
    }

    override suspend fun delete(pokemon: Pokemon) {
        pokemonDao.delete(pokemon.toPokemonEntity())
    }

    override suspend fun deleteAllFavoritePokemons() {
        pokemonDao.deleteAllFavoritePokemons()
    }
}