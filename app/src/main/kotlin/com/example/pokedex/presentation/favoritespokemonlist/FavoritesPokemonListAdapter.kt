package com.example.pokedex.presentation.favoritespokemonlist

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Pokemon
import com.example.pokedex.databinding.PokemonListItemBinding

class FavoritesPokemonListAdapter(private val context: Context) :
    ListAdapter<Pokemon, FavoritesPokemonListAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PokemonListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class ViewHolder(private val binding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(pokemon: Pokemon) {
            binding.apply {
                pokemonIdText.text = pokemon.id.toString()
                pokemonNameText.text = pokemon.name
                pokemonHeightText.text = "${pokemon.height} M"
                pokemonWeightText.text = "${pokemon.weight} KG"
                Glide.with(context).load(pokemon.imageUrl).into(pokemonImage)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon) = oldItem == newItem
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon) = oldItem.id == newItem.id
    }
}