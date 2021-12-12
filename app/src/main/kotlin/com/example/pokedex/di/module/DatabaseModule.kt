package com.example.pokedex.di.module

import android.content.Context
import androidx.room.Room
import com.example.data.source.local.FavoritePokemonsDatabase
import com.example.pokedex.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFavoritePokemonsDatabase(context: Context) = Room.databaseBuilder(
        context.applicationContext,
        FavoritePokemonsDatabase::class.java,
        Constants.DB_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun providePokemonDao(db: FavoritePokemonsDatabase) = db.pokemonDao()
}