package com.example.pokedex.presentation.showrandompokemon

import androidx.lifecycle.*
import com.example.domain.common.Result
import com.example.domain.interactor.PokemonInteractor
import com.example.domain.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShowRandomPokemonViewModel @Inject constructor(
    private val interactor: PokemonInteractor
) : ViewModel() {

    private val _dataLoading = MutableLiveData(false)
    val dataLoading: LiveData<Boolean> get() = _dataLoading

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon> get() = _pokemon

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getPokemonById(id: Int) {
        interactor.getPokemonById(id).onEach { result ->
            when (result) {
                is Result.Loading -> {
                    _dataLoading.postValue(true)
                }
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    _pokemon.postValue(result.data!!)
                }
                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _errorMessage.postValue(result.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addToFavorites(pokemon: Pokemon) {
        viewModelScope.launch(Dispatchers.IO) { interactor.add(pokemon) }
    }

    fun isAddedPokemonWithThisId(id: Int): LiveData<Boolean?> {
        return interactor.isAddedPokemonWithThisId(id).asLiveData()
    }
}