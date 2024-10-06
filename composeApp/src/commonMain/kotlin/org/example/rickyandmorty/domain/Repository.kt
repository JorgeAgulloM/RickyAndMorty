package org.example.rickyandmorty.domain

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.example.rickyandmorty.domain.model.CharacterModel

interface Repository {
    suspend fun getSingleCharacter(id: String): CharacterModel
    fun getAllCharacters(): Flow<PagingData<CharacterModel>>
}