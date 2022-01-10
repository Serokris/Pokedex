package com.example.domain.interactor

import com.example.domain.common.Errors
import com.example.domain.common.Result
import com.example.domain.model.Pokemon
import com.example.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import java.io.IOException
import java.lang.Exception

class GetPokemonByIdTest {

    private val fakePokemonRepository = mock<PokemonRepository>()
    private val pokemonInteractor = PokemonInteractor(fakePokemonRepository)
    private val pokemon = Pokemon(0, "Name", 1f, 1f, "1")

    @Test
    fun `The first value emitted should be is 'Loading'`() = runBlocking {
        Mockito.`when`(fakePokemonRepository.getPokemonById(pokemon.id)).thenReturn(pokemon)
        val result = pokemonInteractor.getPokemonById(0).first()
        assert(result is Result.Loading)
    }

    @Test
    fun `The last value emitted should be is 'Success'`() = runBlocking {
        Mockito.`when`(fakePokemonRepository.getPokemonById(pokemon.id)).thenReturn(pokemon)
        val result = pokemonInteractor.getPokemonById(0).last()
        assert(result is Result.Success && result.data == pokemon)
    }

    @Test
    fun `Expected catching IOException`() = runBlocking {
        Mockito.`when`(fakePokemonRepository.getPokemonById(pokemon.id)).then {
            throw IOException()
        }
        val result = pokemonInteractor.getPokemonById(0).last()
        assert(result is Result.Error && result.message == Errors.FAILED_TO_CONNECT_TO_SERVER)
    }

    @Test
    fun `Expected catching Exception`() = runBlocking {
        Mockito.`when`(fakePokemonRepository.getPokemonById(pokemon.id)).then {
            throw Exception()
        }
        val result = pokemonInteractor.getPokemonById(0).last()
        assert(result is Result.Error && result.message == Errors.FAILED_TO_FIND_POKEMON)
    }
}