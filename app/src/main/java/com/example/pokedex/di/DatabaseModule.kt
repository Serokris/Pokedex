package com.example.pokedex.di

import android.content.Context
import androidx.room.Room
import com.example.pokedex.data.source.local.FavoritePokemonsDatabase
import com.example.pokedex.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideFavoritePokemonsDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context.applicationContext,
        FavoritePokemonsDatabase::class.java,
        Constants.DB_NAME
    ).build()

    @Singleton
    @Provides
    fun providePokemonDao(db: FavoritePokemonsDatabase) = db.pokemonDao()
}