package com.example.pokedex.data.source.remote

import com.example.pokedex.domain.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ) : Response<PokemonResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonById(
        @Path("id") id: Int
    ) : Response<PokemonResponse>
}