package com.example.pokedex.presentation.showrandompokemon

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.domain.common.Result
import com.example.domain.model.Pokemon
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentShowRandomPokemonBinding
import com.example.pokedex.presentation.base.BaseBindingFragment
import com.example.pokedex.utils.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.random.Random

private const val MIN_POKEMON_ID = 1
private const val MAX_POKEMON_ID = 898

class ShowRandomPokemonFragment :
    BaseBindingFragment<FragmentShowRandomPokemonBinding>(FragmentShowRandomPokemonBinding::inflate) {

    private val viewModel: ShowRandomPokemonViewModel by viewModels {
        appComponent.viewModelsFactory()
    }
    private lateinit var pokemonImageUrl: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        binding.apply {
            showRandomPokemonButton.setOnClickListener {
                val random = Random(System.currentTimeMillis())
                val randomPokemonId = random.nextInt(MIN_POKEMON_ID, MAX_POKEMON_ID)

                viewModel.getPokemonById(randomPokemonId).onEach { result ->
                    when (result) {
                        is Result.Success -> {
                            loadingPokemonProgressBar.hideView()
                            pokemonImage.showView()

                            val pokemonData = result.data!!
                            pokemonIdText.text = pokemonData.id.toString()
                            pokemonNameText.text = pokemonData.name.capitalized()
                            pokemonHeightText.text =
                                "${((pokemonData.height * 100F) / 1000F)} M"
                            pokemonWeightText.text =
                                "${((pokemonData.weight * 100F) / 1000F)} KG"
                            Glide.with(requireContext()).load(pokemonData.imageUrl)
                                .into(pokemonImage)
                            pokemonImageUrl = pokemonData.imageUrl
                        }
                        is Result.Loading -> {
                            pokemonImage.hideView()
                            loadingPokemonProgressBar.showView()
                        }
                        is Result.Error -> {
                            loadingPokemonProgressBar.hideView()
                            pokemonImage.setImageResource(R.drawable.ic_not_found)
                            pokemonImage.showView()

                            val textViewList = listOf(
                                pokemonIdText,
                                pokemonNameText,
                                pokemonWeightText,
                                pokemonHeightText
                            )
                            textViewList.onEach { textView -> textView.text = "" }

                            Toast.makeText(
                                requireContext(), result.message, Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }.launchIn(viewModel.viewModelScope)
            }

            addToFavoriteButton.setOnClickListener {
                if (pokemonIdText.text.isNotEmpty()) {
                    val pokemon = Pokemon(
                        pokemonIdText.text.toString().toInt(),
                        pokemonNameText.text.toString(),
                        pokemonHeightText.text.toString().filter { char -> char.isDigit() }
                            .toFloat() / 10F,
                        pokemonWeightText.text.toString().filter { char -> char.isDigit() }
                            .toFloat() / 10F,
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
                            viewModel.addToFavorites(pokemon)
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
    }
}