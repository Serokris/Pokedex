package com.example.pokedex.presentation.showrandompokemon

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.domain.model.Pokemon
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentShowRandomPokemonBinding
import com.example.pokedex.presentation.base.BaseBindingFragment
import com.example.pokedex.utils.*
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

        subscribeUi()
        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun subscribeUi() {
        binding.apply {
            viewModel.pokemon.observe(viewLifecycleOwner, { pokemonData ->
                pokemonData?.let { pokemon ->
                    pokemonIdText.text = pokemon.id.toString()
                    pokemonNameText.text = pokemon.name.capitalized()
                    pokemonHeightText.text =
                        "${((pokemon.height * 100F) / 1000F)} M"
                    pokemonWeightText.text =
                        "${((pokemon.weight * 100F) / 1000F)} KG"
                    Glide.with(requireContext()).load(pokemon.imageUrl)
                        .into(pokemonImage)
                    pokemonImageUrl = pokemon.imageUrl
                }
            })

            viewModel.dataLoading.observe(viewLifecycleOwner, { isLoading ->
                if (isLoading) {
                    loadingPokemonProgressBar.showView()
                    pokemonImage.hideView()
                } else {
                    loadingPokemonProgressBar.hideView()
                    pokemonImage.showView()
                }
            })

            viewModel.errorMessage.observe(viewLifecycleOwner, { errorMessage ->
                if (errorMessage.isNotEmpty()) {
                    pokemonImage.setImageResource(R.drawable.ic_not_found)

                    val textViewList = listOf(
                        pokemonIdText,
                        pokemonNameText,
                        pokemonWeightText,
                        pokemonHeightText
                    )
                    textViewList.onEach { textView -> textView.text = "" }

                    Toast.makeText(
                        requireContext(), errorMessage, Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }

    private fun initViews() {
        binding.apply {
            showRandomPokemonButton.setOnClickListener {
                val random = Random(System.currentTimeMillis())
                val randomPokemonId = random.nextInt(MIN_POKEMON_ID, MAX_POKEMON_ID)
                viewModel.getPokemonById(randomPokemonId)
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