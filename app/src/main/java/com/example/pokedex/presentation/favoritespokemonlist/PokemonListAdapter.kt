package com.example.pokedex.presentation.favoritespokemonlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.databinding.PokemonListItemBinding

class PokemonListAdapter(private val context: Context) :
    ListAdapter<Pokemon, PokemonListAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PokemonListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class ViewHolder(private val binding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon) {
            binding.apply {
                pokemonIdText.text = pokemon.id.toString()
                pokemonNameText.text = pokemon.name
                pokemonHeightText.text = pokemon.height
                pokemonWeightText.text = pokemon.weight
                Glide.with(context).load(pokemon.imageUrl).into(pokemonImage)
                executePendingBindings()
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon) = oldItem == newItem
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon) = oldItem.id == newItem.id
    }
}