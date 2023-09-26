package com.example.pruebaapplication.data.repository

import androidx.lifecycle.map
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import androidx.paging.map
import com.example.pruebaapplication.data.database.dao.PokemonDao
import com.example.pruebaapplication.data.network.PokemonClient
import com.example.pruebaapplication.data.util.Constants.DEFAULT_PAGE_SIZE
import com.example.pruebaapplication.data.util.Constants.DEFAULT_PAGING_KEY
import com.example.pruebaapplication.domain.model.toDomain
import com.example.pruebaapplication.paging.PokemonPagingSource
import javax.inject.Inject


class PokemonRepositoryImpl @Inject constructor(
    private val pokemonClient: PokemonClient,
    private val pokemonDao: PokemonDao
) : PokemonRepository {

    override fun getPokemons() = Pager(
        config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE),
        initialKey = DEFAULT_PAGING_KEY,
        pagingSourceFactory = { PokemonPagingSource(pokemonClient, pokemonDao) }
    ).liveData.map { pagingData ->
        pagingData.map { it.toDomain() }
    }
}
