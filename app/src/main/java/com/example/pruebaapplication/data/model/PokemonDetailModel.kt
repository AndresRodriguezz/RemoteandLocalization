package com.example.pruebaapplication.data.model

import com.google.gson.annotations.SerializedName

data class PokemonDetailModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("base_experience")
    val experience: Int,
    @SerializedName("types")
    val types: List<TypeResponseModel>
)

data class TypeResponseModel(
    @SerializedName("type")
    val type: TypeModel
)

data class TypeModel(
    @SerializedName("name")
    val name: String
)
