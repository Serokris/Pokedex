package com.example.pokedex.data.source.local.dao

import androidx.room.*
import com.example.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert
    suspend fun insert(pokemon: Pokemon)

    @Delete
    suspend fun delete(pokemon: Pokemon)

    @Query("DELETE FROM `pokemon-table`")
    suspend fun deleteAllFavoritePokemons()

    @Query("SELECT id FROM `pokemon-table` WHERE id == :id")
    suspend fun isAddedPokemonWithThisId(id: Int): Boolean?

    @Query("SELECT * FROM `pokemon-table`")
    fun getAllFavoritePokemons(): Flow<List<Pokemon>>
}