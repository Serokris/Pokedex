package com.example.pokedex.di.module

import com.example.pokedex.data.source.remote.PokeApiService
import com.example.pokedex.common.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    @Singleton
    @Provides
    fun providePokeApiService(
        baseUrl: String,
        coroutineCallAdapterFactory: CoroutineCallAdapterFactory,
        gsonConverterFactory: GsonConverterFactory
    ): PokeApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(coroutineCallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(PokeApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideCoroutineCallAdapterFactory(): CoroutineCallAdapterFactory {
        return CoroutineCallAdapterFactory.invoke()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun providePokeApiServiceBaseUrl(): String {
        return Constants.BASE_URL
    }
}