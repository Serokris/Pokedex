package com.example.pokedex.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.model.response.PokemonResponse
import com.example.pokedex.domain.interactor.PokemonInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonInteractor: PokemonInteractor
) : ViewModel() {
    suspend fun getPokemonByName(name: String): Response<PokemonResponse> {
        return withContext(Dispatchers.IO) { pokemonInteractor.getPokemonByName(name) }
    }

    suspend fun getPokemonById(id: Int): Response<PokemonResponse> {
        return withContext(Dispatchers.IO) { pokemonInteractor.getPokemonById(id) }
    }

    fun getAllFavoritePokemons(): LiveData<List<Pokemon>> {
        return pokemonInteractor.getAllFavoritePokemons().asLiveData()
    }

    suspend fun isAddedPokemonWithThisId(id: Int): Boolean? {
        return withContext(Dispatchers.IO) { pokemonInteractor.isAddedPokemonWithThisId(id) }
    }

    fun insert(pokemon: Pokemon) {
        viewModelScope.launch(Dispatchers.IO) { pokemonInteractor.insert(pokemon) }
    }

    fun delete(pokemon: Pokemon) {
        viewModelScope.launch(Dispatchers.IO) { pokemonInteractor.delete(pokemon) }
    }

    fun deleteAllFavoritePokemons() {
        viewModelScope.launch(Dispatchers.IO) { pokemonInteractor.deleteAllFavoritePokemons() }
    }
}