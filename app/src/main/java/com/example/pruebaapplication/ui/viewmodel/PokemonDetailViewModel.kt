package com.example.pruebaapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebaapplication.domain.model.PokemonDetail
import com.example.pruebaapplication.domain.usecase.GetPokemonDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetail: GetPokemonDetail
): ViewModel() {

    private val _pokemonDetail = MutableLiveData<PokemonDetail>()
    val pokemonDetail: LiveData<PokemonDetail> = _pokemonDetail
    private val _pokemonDetailFailure = MutableLiveData<Boolean>()
    val pokemonDetailFailure: LiveData<Boolean> = _pokemonDetailFailure

    fun PokemonDetail(name: String) {
        viewModelScope.launch {
            val result = getPokemonDetail(name)
            result.onSuccess {
                _pokemonDetail.postValue(it)
            }.onFailure {
                    _pokemonDetailFailure.postValue(true)
                }
        }
    }
}