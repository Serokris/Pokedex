package com.example.data.models.db_entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon-table")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val height: Float,
    val weight: Float,
    val imageUrl: String
)