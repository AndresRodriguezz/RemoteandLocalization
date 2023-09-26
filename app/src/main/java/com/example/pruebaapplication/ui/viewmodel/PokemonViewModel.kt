package com.example.pruebaapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pruebaapplication.data.repository.PokemonRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(repository: PokemonRepositoryImpl
) : ViewModel() {

    val listPokemon = repository.getPokemons().cachedIn(viewModelScope)
}
