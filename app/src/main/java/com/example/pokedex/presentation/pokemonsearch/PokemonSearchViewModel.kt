package com.example.pokedex.presentation.pokemonsearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.interactor.PokemonInteractor
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.model.response.PokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PokemonSearchViewModel @Inject constructor(
    private val interactor: PokemonInteractor
) : ViewModel() {
    suspend fun getPokemonByName(name: String): Response<PokemonResponse> {
        return withContext(Dispatchers.IO) { interactor.getPokemonByName(name) }
    }

    fun addToFavorites(pokemon: Pokemon) {
        viewModelScope.launch(Dispatchers.IO) { interactor.insert(pokemon) }
    }

    fun isAddedPokemonWithThisId(id: Int): LiveData<Boolean?> {
        return interactor.isAddedPokemonWithThisId(id).asLiveData()
    }
}