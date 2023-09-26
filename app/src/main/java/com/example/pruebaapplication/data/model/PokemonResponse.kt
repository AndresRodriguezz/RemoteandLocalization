package com.example.pruebaapplication.data.model

import com.example.pruebaapplication.data.util.Constants.PARAM_COUNT
import com.example.pruebaapplication.data.util.Constants.PARAM_NEXT
import com.example.pruebaapplication.data.util.Constants.PARAM_PREVIOUS
import com.example.pruebaapplication.data.util.Constants.PARAM_RESULTS
import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName(PARAM_COUNT)
    val count: Int,
    @SerializedName(PARAM_NEXT)
    val next: String?,
    @SerializedName(PARAM_PREVIOUS)
    val previous: String?,
    @SerializedName(PARAM_RESULTS)
    val results: List<PokemonModel>
)
