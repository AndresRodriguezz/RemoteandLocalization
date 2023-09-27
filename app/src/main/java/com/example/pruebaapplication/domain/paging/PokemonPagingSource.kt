package com.example.pruebaapplication.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pruebaapplication.data.database.dao.PokemonDao
import com.example.pruebaapplication.data.database.entities.toEntity
import com.example.pruebaapplication.data.model.PokemonModel
import com.example.pruebaapplication.data.model.PokemonResponse
import com.example.pruebaapplication.data.model.toPokemonModel
import com.example.pruebaapplication.data.network.PokemonClient
import com.example.pruebaapplication.data.util.Constants.DEFAULT_PAGING_OFFSET
import com.example.pruebaapplication.data.util.Constants.DEFAULT_PAGING_SIZE
import com.example.pruebaapplication.domain.util.Constants.PAGE_INDEX
import retrofit2.HttpException
import retrofit2.Response

class PokemonPagingSource(
    private val pokemonClient: PokemonClient,
    private val pokemonDao: PokemonDao
) : PagingSource<Int, PokemonModel>() {

    override fun getRefreshKey(state: PagingState<Int, PokemonModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(PAGE_INDEX) ?: anchorPage?.nextKey?.minus(PAGE_INDEX)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonModel> {
        val page = params.key ?: PAGE_INDEX
        try {
            val localData = pokemonDao.getAllPokemon()

            if (localData.value?.isNotEmpty() == true) {
                return LoadResult.Page(
                    data = localData.value!!.map { it.toPokemonModel() },
                    prevKey = null,
                    nextKey = if (localData.value?.isEmpty() == true) null else page + PAGE_INDEX
                )
            }

            val response: Response<PokemonResponse> = if (page < 1) {
                pokemonClient.pokemonList()
            } else {
                pokemonClient.pokemonList(
                    page = page * DEFAULT_PAGING_SIZE,
                    offset = page * DEFAULT_PAGING_OFFSET
                )
            }

            response.body()?.results?.let { pokemonList ->
                pokemonDao.insertPokemon(pokemonList.map { it.toEntity() })
            }

            return LoadResult.Page(
                data = response.body()?.results ?: emptyList(),
                prevKey = null,
                nextKey = if (response.body()?.results?.isEmpty() == true) null else page + PAGE_INDEX
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}
