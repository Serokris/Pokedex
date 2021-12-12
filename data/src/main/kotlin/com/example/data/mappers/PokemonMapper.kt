package com.example.data.mappers

import com.example.data.models.db_entities.PokemonEntity
import com.example.data.models.responses.PokemonResponse
import com.example.domain.model.Pokemon

fun Pokemon.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(id, name, height, weight, imageUrl)
}

fun PokemonEntity.toPokemon(): Pokemon {
    return Pokemon(id, name, height, weight, imageUrl)
}

fun PokemonResponse.toPokemon(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        height = height.toFloat(),
        weight = weight.toFloat(),
        imageUrl = sprites.frontDefault,
    )
}