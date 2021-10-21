package com.example.pokedex.presentation.showrandompokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Result
import com.example.domain.interactor.PokemonInteractor
import com.example.domain.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShowRandomPokemonViewModel @Inject constructor(
    private val interactor: PokemonInteractor
) : ViewModel() {

    fun getPokemonById(id: Int): Flow<Result<Pokemon>> = interactor.getPokemonById(id)

    fun addToFavorites(pokemon: Pokemon) {
        viewModelScope.launch(Dispatchers.IO) { interactor.insert(pokemon) }
    }

    fun isAddedPokemonWithThisId(id: Int): LiveData<Boolean?> {
        return interactor.isAddedPokemonWithThisId(id).asLiveData()
    }
}