package com.example.pokedex.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedex.data.source.local.dao.PokemonDao
import com.example.pokedex.domain.model.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class FavoritePokemonsDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}