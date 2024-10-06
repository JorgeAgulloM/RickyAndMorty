package org.example.rickyandmorty.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.ktor.utils.io.errors.IOException
import org.example.rickyandmorty.data.remote.ApiService
import org.example.rickyandmorty.domain.model.EpisodeModel

class EpisodesPagingSource(private val api: ApiService) : PagingSource<Int, EpisodeModel>() {
    override fun getRefreshKey(state: PagingState<Int, EpisodeModel>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeModel> = try {
        val page = params.key ?: 1
        val response = api.getAllEpisodes(page)
        val episodes = response.results.map { it.toDomain() }

        val prevKey = if (page > 1) page - 1 else null
        val nextKey = if (response.info.next != null) page + 1 else null

        LoadResult.Page(episodes, prevKey, nextKey)

    } catch (ioException: IOException) {
        LoadResult.Error(ioException)
    } catch (exception: Exception) {
        LoadResult.Error(exception)
    }
}