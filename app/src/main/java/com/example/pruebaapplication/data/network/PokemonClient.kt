package com.example.pruebaapplication.data.network

import com.example.pruebaapplication.data.model.PokemonDetailModel
import com.example.pruebaapplication.data.model.PokemonResponse
import com.example.pruebaapplication.data.util.Constants.DEFAULT_OFFSET
import com.example.pruebaapplication.data.util.Constants.DEFAULT_PAGING_SIZE
import retrofit2.Response
import javax.inject.Inject

class PokemonClient @Inject constructor(private val pokemonService: PokemonService) {

    suspend fun pokemonList(
        page: Int = DEFAULT_PAGING_SIZE,
        offset: Int = DEFAULT_OFFSET
    ): Response<PokemonResponse> = pokemonService.pokemonList(
        limit = page,
        offset =  offset
    )

    suspend fun pokemonDetail(
        name: String
    ): Response<PokemonDetailModel> = pokemonService.pokemonDetails(
        name = name
    )
}
