package com.example.pruebaapplication.data.network

import com.example.pruebaapplication.data.model.PokemonDetailModel
import com.example.pruebaapplication.data.model.PokemonResponse
import com.example.pruebaapplication.data.util.Constants.DEFAULT_ITEMS_PER_PAGE
import com.example.pruebaapplication.data.util.Constants.DEFAULT_OFFSET
import com.example.pruebaapplication.data.util.Constants.END_POINT_GET_POKEMONS
import com.example.pruebaapplication.data.util.Constants.PARAM_LIMIT
import com.example.pruebaapplication.data.util.Constants.PARAM_OFFSET
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET(END_POINT_GET_POKEMONS)
    suspend fun pokemonList(
        @Query(PARAM_LIMIT) limit: Int = DEFAULT_ITEMS_PER_PAGE,
        @Query(PARAM_OFFSET) offset: Int = DEFAULT_OFFSET
    ): Response<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun pokemonDetails(
        @Path("name") name: String
    ): Response<PokemonDetailModel>
}
