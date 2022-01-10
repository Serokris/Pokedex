package com.example.domain.interactor

import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class IsAddedPokemonWithThisIdTest {

    private val fakePokemonRepository = mock<PokemonRepository>()
    private val pokemonInteractor = PokemonInteractor(fakePokemonRepository)

    @Test
    fun `Should return the true if the pokemon has already been added`() = runBlocking {
        val pokemonId = 0
        Mockito.`when`(fakePokemonRepository.isAddedPokemonWithThisId(pokemonId)).thenReturn(
            flow { emit(true) }
        )
        assert(pokemonInteractor.isAddedPokemonWithThisId(pokemonId).single())
    }

    @Test
    fun `Should return the false if the pokemon has not been added`() = runBlocking {
        val pokemonId = 0
        Mockito.`when`(fakePokemonRepository.isAddedPokemonWithThisId(pokemonId)).thenReturn(
            flow { emit(false) }
        )
        assert(!pokemonInteractor.isAddedPokemonWithThisId(pokemonId).single())
    }
}