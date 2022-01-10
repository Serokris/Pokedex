package com.example.domain.interactor

import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class DeleteAllFavoritePokemonsTest {

    private val fakePokemonRepository = mock<PokemonRepository>()
    private val pokemonInteractor = PokemonInteractor(fakePokemonRepository)

    @Test
    fun `Should be called 'deleteAllFavoritePokemons' method of the repository`() = runBlocking {
        pokemonInteractor.deleteAllFavoritePokemons()
        verify(fakePokemonRepository).deleteAllFavoritePokemons()
    }
}