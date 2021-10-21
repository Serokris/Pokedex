package com.example.pokedex.presentation.pokemonsearch

import androidx.lifecycle.*
import com.example.domain.common.Result
import com.example.domain.interactor.PokemonInteractor
import com.example.domain.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonSearchViewModel @Inject constructor(
    private val interactor: PokemonInteractor
) : ViewModel() {

    fun getPokemonByName(name: String): Flow<Result<Pokemon>> = interactor.getPokemonByName(name)

    fun addToFavorites(pokemon: Pokemon) {
        viewModelScope.launch(Dispatchers.IO) { interactor.insert(pokemon) }
    }

    fun isAddedPokemonWithThisId(id: Int): LiveData<Boolean?> {
        return interactor.isAddedPokemonWithThisId(id).asLiveData()
    }
}