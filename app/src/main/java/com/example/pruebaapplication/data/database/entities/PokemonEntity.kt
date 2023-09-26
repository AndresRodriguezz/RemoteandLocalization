package com.example.pruebaapplication.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pruebaapplication.data.model.PokemonModel

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val url: String,
    val imageUrl: String
)

 fun PokemonModel.toEntity() = PokemonEntity(
     id = getId(),
     name = name,
     url = url,
     imageUrl = getImageUrl()
 )
