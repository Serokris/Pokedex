package com.example.pokedex.di.module

import android.content.Context
import androidx.room.Room
import com.example.pokedex.data.source.local.FavoritePokemonsDatabase
import com.example.pokedex.common.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideFavoritePokemonsDatabase(context: Context) = Room.databaseBuilder(
        context.applicationContext,
        FavoritePokemonsDatabase::class.java,
        Constants.DB_NAME
    ).build()

    @Singleton
    @Provides
    fun providePokemonDao(db: FavoritePokemonsDatabase) = db.pokemonDao()
}