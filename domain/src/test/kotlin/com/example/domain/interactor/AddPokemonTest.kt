package com.example.domain.interactor

import com.example.domain.model.Pokemon
import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class AddPokemonTest {

    private val fakePokemonRepository = mock<PokemonRepository>()
    private val pokemonInteractor = PokemonInteractor(fakePokemonRepository)
    private val pokemon = Pokemon(0, "Charizard", 120f, 70f, "")

    @Test
    fun `Should be called 'insert' method of the repository`() = runBlocking {
        pokemonInteractor.add(pokemon)
        verify(fakePokemonRepository).insert(pokemon)
    }
}