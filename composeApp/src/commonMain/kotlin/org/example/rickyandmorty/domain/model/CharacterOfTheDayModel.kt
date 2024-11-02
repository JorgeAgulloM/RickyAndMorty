package org.example.rickyandmorty.domain.model

import org.example.rickyandmorty.data.database.entity.CharacterOfTheDayEntity

data class CharacterOfTheDayModel(
    val characterModel: CharacterModel,
    val selectedDay: String
) {
    fun toEntity(): CharacterOfTheDayEntity = CharacterOfTheDayEntity(
        id = characterModel.id,
        isAlive = characterModel.isAlive,
        image = characterModel.image,
        name = characterModel.name,
        selectedDate = selectedDay,
        species = characterModel.species
    )
}