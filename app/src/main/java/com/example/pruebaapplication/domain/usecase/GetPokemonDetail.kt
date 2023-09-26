package com.example.pruebaapplication.domain.usecase

import com.example.pruebaapplication.data.repository.PokemonDetailRepository
import com.example.pruebaapplication.domain.model.PokemonDetail
import javax.inject.Inject

class GetPokemonDetail @Inject constructor(private val repository: PokemonDetailRepository) {

    suspend operator fun invoke(name: String): Result<PokemonDetail> {
        return repository.getPokemonsDetail(name)
    }
}