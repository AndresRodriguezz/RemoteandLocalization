package com.example.pruebaapplication.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_detail")
data class PokemonDetailEntity(
    @PrimaryKey
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
)
