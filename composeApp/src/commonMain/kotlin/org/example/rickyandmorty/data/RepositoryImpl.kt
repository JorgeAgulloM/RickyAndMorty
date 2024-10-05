package org.example.rickyandmorty.data

import org.example.rickyandmorty.data.remote.ApiService
import org.example.rickyandmorty.domain.Repository
import org.example.rickyandmorty.domain.model.CharacterModel

class RepositoryImpl(private val api: ApiService): Repository {
    override suspend fun getSingleCharacter(id: String): CharacterModel {
        return api.getSingleCharacter(id).toDomain()
    }
}