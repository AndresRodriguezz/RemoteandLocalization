package com.example.pruebaapplication.domain.model

import com.example.pruebaapplication.data.model.PokemonDetailModel
import com.example.pruebaapplication.data.model.TypeModel
import com.example.pruebaapplication.data.model.TypeResponseModel

data class PokemonDetail(
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val types: List<TypeResponse>
)

data class TypeResponse(
    val type: Type
)

data class Type(
    val name: String
)

fun TypeResponseModel.toDomain() = TypeResponse (
    type = type.toDomain()
)

fun PokemonDetailModel.toDomain() = PokemonDetail(
    name = name,
    height = height,
    weight = weight,
    experience =experience,
    types = types.map { it.toDomain() }
)

fun TypeModel.toDomain() = Type(
    name = name
)
