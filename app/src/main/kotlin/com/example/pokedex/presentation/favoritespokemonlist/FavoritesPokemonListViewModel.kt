package com.example.pokedex.presentation.favoritespokemonlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.interactor.PokemonInteractor
import com.example.domain.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesPokemonListViewModel @Inject constructor(
    private val interactor: PokemonInteractor
) : ViewModel() {

    fun addToFavorites(pokemon: Pokemon) {
        viewModelScope.launch(Dispatchers.IO) { interactor.add(pokemon) }
    }

    fun getAllFavoritePokemons(): LiveData<List<Pokemon>> {
        return interactor.getAllFavoritePokemons().asLiveData()
    }

    fun deleteAllFavoritePokemons() {
        viewModelScope.launch(Dispatchers.IO) { interactor.deleteAllFavoritePokemons() }
    }

    fun delete(pokemon: Pokemon) {
        viewModelScope.launch(Dispatchers.IO) { interactor.delete(pokemon) }
    }
}