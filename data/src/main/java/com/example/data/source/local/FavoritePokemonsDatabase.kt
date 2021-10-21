package com.example.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.models.db_entities.PokemonEntity
import com.example.data.source.local.dao.PokemonDao

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
abstract class FavoritePokemonsDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}