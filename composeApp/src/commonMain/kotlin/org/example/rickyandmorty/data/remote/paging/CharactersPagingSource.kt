package org.example.rickyandmorty.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.ktor.utils.io.errors.IOException
import org.example.rickyandmorty.data.remote.ApiService
import org.example.rickyandmorty.domain.model.CharacterModel

class CharactersPagingSource(private val api: ApiService) : PagingSource<Int, CharacterModel>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> = try {
        val page = params.key ?: 1
        val response = api.getAllCharacters(page)
        val characters = response.result

        val prevKey = if (page > 0) -1 else null
        val nextKey = if (response.info.next != null) page + 1 else null

        LoadResult.Page(
            data = characters.map { characterResponse -> characterResponse.toDomain() },
            prevKey = prevKey,
            nextKey = nextKey
        )

    } catch (ioException: IOException) {
        LoadResult.Error(ioException)
    } catch (exception: Exception) {
        LoadResult.Error(exception)
    }
}