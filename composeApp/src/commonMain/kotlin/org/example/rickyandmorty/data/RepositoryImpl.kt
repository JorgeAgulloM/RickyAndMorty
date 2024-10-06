package org.example.rickyandmorty.data

import androidx.paging.PagingConfig
import app.cash.paging.Pager
import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.example.rickyandmorty.data.database.RickyAndMortyDatabase
import org.example.rickyandmorty.data.remote.ApiService
import org.example.rickyandmorty.data.remote.paging.CharactersPagingSource
import org.example.rickyandmorty.domain.Repository
import org.example.rickyandmorty.domain.model.CharacterModel

class RepositoryImpl(
    private val api: ApiService,
    private val charactersPagingSource: CharactersPagingSource,
    private val database: RickyAndMortyDatabase
) : Repository {

    companion object {
        const val MAX_ITEMS = 20
        const val PREFETCH_ITEMS = 5
    }

    override suspend fun getSingleCharacter(id: String): CharacterModel =
        api.getSingleCharacter(id).toDomain()

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> = Pager(
        config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
        pagingSourceFactory = { charactersPagingSource }
    ).flow

    override suspend fun getCharacterDB() {
        database.getPreferenceDao().getCharacterOfTheDayDb()
    }

}