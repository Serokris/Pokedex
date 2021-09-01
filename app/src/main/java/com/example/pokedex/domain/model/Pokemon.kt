package com.example.pokedex.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon-table")
data class Pokemon(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val height: String,
    val weight: String,
    val imageUrl: String
)