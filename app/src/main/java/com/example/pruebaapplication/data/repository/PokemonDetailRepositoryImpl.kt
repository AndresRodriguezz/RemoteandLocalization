package com.example.pruebaapplication.data.repository

import android.util.Log
import com.example.pruebaapplication.data.network.PokemonClient
import com.example.pruebaapplication.domain.model.PokemonDetail
import com.example.pruebaapplication.domain.model.toDomain
import java.io.IOException
import javax.inject.Inject

class PokemonDetailRepositoryImpl @Inject constructor(
    private val pokemonClient: PokemonClient
) : PokemonDetailRepository {
    override suspend fun getPokemonsDetail(name: String): Result<PokemonDetail> {
        return try {
            val response = pokemonClient.pokemonDetail(name)
            val pokemonDetail = response.body()!!.toDomain()
            Result.success(pokemonDetail)
        } catch (e: IOException) {
            Log.i("NetworkError", "network-related", e)
            Result.failure(Throwable(e))
        } catch (e: Throwable) {
            Log.i("NetworkError", "other exceptions", e)
            Result.failure(Throwable(e))
        }
    }
}