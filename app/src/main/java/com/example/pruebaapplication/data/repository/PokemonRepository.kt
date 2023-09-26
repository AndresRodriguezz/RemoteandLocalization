package com.example.pruebaapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.pruebaapplication.domain.model.Pokemon

interface PokemonRepository {

    fun getPokemons(): LiveData<PagingData<Pokemon>>
}
