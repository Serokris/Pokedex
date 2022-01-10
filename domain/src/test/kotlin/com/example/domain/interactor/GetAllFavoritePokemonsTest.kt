package com.example.domain.interactor

import com.example.domain.model.Pokemon
import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetAllFavoritePokemonsTest {

    private val fakePokemonRepository = mock<PokemonRepository>()
    private val pokemonInteractor = PokemonInteractor(fakePokemonRepository)
    private val pokemonList = listOf(
        Pokemon(0, "Name 1", 1f, 1f, "1"),
        Pokemon(1, "Name 2", 2f, 2f, "2"),
        Pokemon(2, "Name 3", 3f, 3f, "3")
    )

    @Test
    fun `Should be return list of all favorite pokemons`() = runBlocking {
        Mockito.`when`(fakePokemonRepository.getAllFavoritePokemons()).thenReturn(
            flow { emit(pokemonList) }
        )
        val pokemonList = pokemonInteractor.getAllFavoritePokemons().single()
        assert(pokemonList.isNotEmpty())
    }
}