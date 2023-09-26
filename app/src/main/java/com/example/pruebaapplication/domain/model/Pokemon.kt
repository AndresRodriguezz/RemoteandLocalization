package com.example.pruebaapplication.domain.model

import com.example.pruebaapplication.data.model.PokemonModel

data class Pokemon(
    val id: Int,
    val name: String,
    val url: String,
    val imageUrl: String
)

fun PokemonModel.toDomain() = Pokemon (
    id = getId(),
    name = name,
    url = url,
    imageUrl = getImageUrl()
)
