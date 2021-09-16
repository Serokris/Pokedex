package com.example.pokedex.presentation.showrandompokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.common.appComponent
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.common.thereIsInternetConnection
import com.example.pokedex.databinding.FragmentShowRandomPokemonBinding
import com.example.pokedex.common.capitalized
import com.example.pokedex.presentation.viewmodel.PokemonViewModel
import com.example.pokedex.common.observeOnce
import kotlinx.coroutines.launch
import kotlin.random.Random

private const val MIN_POKEMON_ID = 1
private const val MAX_POKEMON_ID = 898

class ShowRandomPokemonFragment : Fragment() {

    private val viewModel: PokemonViewModel by viewModels { appComponent.viewModelsFactory() }
    private lateinit var pokemonImageUrl: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentShowRandomPokemonBinding.inflate(inflater)

        binding.apply {
            showRandomPokemonButton.setOnClickListener {
                if (thereIsInternetConnection(requireContext())) {
                    viewModel.viewModelScope.launch {
                        val random = Random(System.currentTimeMillis())
                        val randomPokemonId = random.nextInt(MIN_POKEMON_ID, MAX_POKEMON_ID)
                        val randomPokemon = viewModel.getPokemonById(randomPokemonId).body()

                        if (randomPokemon != null) {
                            pokemonIdText.text = randomPokemon.id.toString()
                            pokemonNameText.text = randomPokemon.name.capitalized()
                            pokemonHeightText.text = randomPokemon.height.toString()
                            pokemonWeightText.text = randomPokemon.weight.toString()
                            Glide.with(requireContext()).load(randomPokemon.sprites.frontDefault)
                                .into(pokemonImage)
                            pokemonImageUrl = randomPokemon.sprites.frontDefault
                        } else {
                            Toast.makeText(
                                requireContext(), R.string.failed_to_generate_pokemon,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        requireContext(), R.string.no_internet_connection,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            addToFavoriteButton.setOnClickListener {
                if (pokemonIdText.text.isNotEmpty()) {
                    val pokemon = Pokemon(
                        pokemonIdText.text.toString().toInt(),
                        pokemonNameText.text.toString(),
                        pokemonHeightText.text.toString(),
                        pokemonWeightText.text.toString(),
                        pokemonImageUrl
                    )
                    viewModel.isAddedPokemonWithThisId(pokemon.id).observeOnce { isAdded ->
                        if (isAdded == true) {
                            Toast.makeText(
                                requireContext(),
                                R.string.this_pokemon_added_to_favorites,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            viewModel.insert(pokemon)
                            Toast.makeText(
                                requireContext(),
                                R.string.pokemon_has_been_added,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        requireContext(), R.string.failed_to_add_pokemon,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        return binding.root
    }
}