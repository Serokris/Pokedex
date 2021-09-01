package com.example.pokedex.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.model.PokemonResponse
import com.example.pokedex.domain.usecases.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase
) : ViewModel() {
    suspend fun getPokemonByName(name: String): Response<PokemonResponse> {
        return pokemonUseCase.getPokemonByName(name)
    }

    suspend fun getPokemonById(id: Int): Response<PokemonResponse> {
        return pokemonUseCase.getPokemonById(id)
    }

    fun getAllFavoritePokemons(): LiveData<List<Pokemon>> {
        return pokemonUseCase.getAllFavoritePokemons().asLiveData()
    }

    suspend fun isAddedPokemonWithThisId(id: Int): Boolean? {
        return pokemonUseCase.isAddedPokemonWithThisId(id)
    }

    fun insert(pokemon: Pokemon) {
        viewModelScope.launch { pokemonUseCase.insert(pokemon) }
    }

    fun delete(pokemon: Pokemon) {
        viewModelScope.launch { pokemonUseCase.delete(pokemon) }
    }

    fun deleteAllFavoritePokemons() {
        viewModelScope.launch { pokemonUseCase.deleteAllFavoritePokemons() }
    }
}