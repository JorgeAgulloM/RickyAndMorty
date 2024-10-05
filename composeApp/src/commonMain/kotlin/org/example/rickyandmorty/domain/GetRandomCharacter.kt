package org.example.rickyandmorty.domain

import org.example.rickyandmorty.domain.model.CharacterModel

class GetRandomCharacter(private val repository: Repository) {
    suspend operator fun invoke(): CharacterModel = (1..826).random().toString().let { id ->
        repository.getSingleCharacter(id)
    }
}
