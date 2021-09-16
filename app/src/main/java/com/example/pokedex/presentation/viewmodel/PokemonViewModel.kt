package com.example.pokedex.presentation.viewmodel

import androidx.lifecycle.*
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.model.response.PokemonResponse
import com.example.pokedex.domain.interactor.PokemonInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

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

    fun isAddedPokemonWithThisId(id: Int): LiveData<Boolean?> {
        return pokemonInteractor.isAddedPokemonWithThisId(id).asLiveData()
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