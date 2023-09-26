package com.example.pruebaapplication.data.repository

import com.example.pruebaapplication.domain.model.PokemonDetail

interface PokemonDetailRepository {

   suspend fun getPokemonsDetail(name: String) : Result<PokemonDetail>
}