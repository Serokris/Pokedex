package com.example.pokedex.di.module

import com.example.data.source.remote.PokeApiService
import com.example.pokedex.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
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

    @Provides
    @Singleton
    fun provideCoroutineCallAdapterFactory(): CoroutineCallAdapterFactory {
        return CoroutineCallAdapterFactory.invoke()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providePokeApiServiceBaseUrl(): String {
        return Constants.BASE_URL
    }
}