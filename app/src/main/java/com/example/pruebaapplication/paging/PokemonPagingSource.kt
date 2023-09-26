package com.example.pruebaapplication.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pruebaapplication.data.model.PokemonModel
import com.example.pruebaapplication.data.model.PokemonResponse
import com.example.pruebaapplication.data.network.PokemonClient
import com.example.pruebaapplication.data.util.Constants.DEFAULT_PAGING_OFFSET
import com.example.pruebaapplication.data.util.Constants.DEFAULT_PAGING_SIZE
import com.example.pruebaapplication.domain.util.Constants.PAGE_INDEX
import retrofit2.HttpException
import retrofit2.Response

class PokemonPagingSource(
    private val pokemonClient: PokemonClient
) : PagingSource<Int, PokemonModel>() {

    override fun getRefreshKey(state: PagingState<Int, PokemonModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(PAGE_INDEX) ?: anchorPage?.nextKey?.minus(PAGE_INDEX)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonModel> {
        val page = params.key ?: PAGE_INDEX
        return try {
            val response: Response<PokemonResponse> = if (page < 1) {
                pokemonClient.pokemonList()
            } else {
                pokemonClient.pokemonList(
                    page = page * DEFAULT_PAGING_SIZE,
                    offset = page * DEFAULT_PAGING_OFFSET
                )
            }
            LoadResult.Page(
                data = response.body()?.results ?: emptyList(),
                prevKey = if (page == PAGE_INDEX || page == 0) null else page - PAGE_INDEX,
                nextKey = if (response.body()?.results?.isEmpty() == true) null else page + PAGE_INDEX
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}
