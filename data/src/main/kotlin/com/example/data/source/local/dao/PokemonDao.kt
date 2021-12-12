package com.example.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.models.db_entities.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert
    suspend fun insert(pokemon: PokemonEntity)

    @Delete
    suspend fun delete(pokemon: PokemonEntity)

    @Query("DELETE FROM `pokemon-table`")
    suspend fun deleteAllFavoritePokemons()

    @Query("SELECT id FROM `pokemon-table` WHERE id == :id")
    fun isAddedPokemonWithThisId(id: Int): Flow<Boolean>

    @Query("SELECT * FROM `pokemon-table`")
    fun getAllFavoritePokemons(): Flow<List<PokemonEntity>>
}