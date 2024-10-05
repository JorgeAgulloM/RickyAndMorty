package org.example.rickyandmorty.domain

import org.example.rickyandmorty.domain.model.CharacterModel

interface Repository {
    suspend fun getSingleCharacter(id: String): CharacterModel
}