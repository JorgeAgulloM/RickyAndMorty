package org.example.rickyandmorty.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.json.Json
import org.example.rickyandmorty.domain.model.CharacterModel
import org.example.rickyandmorty.domain.model.CharacterOfTheDayModel

@Entity
data class CharacterOfTheDayEntity(
    @PrimaryKey
    val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val selectedDate: String,
    val species: String,
    val gender: String,
    val origin: String,
    val episodes: String
) {
    fun toDomain(): CharacterOfTheDayModel = CharacterOfTheDayModel(
        characterModel = CharacterModel(
            id = id,
            isAlive = isAlive,
            image = image,
            name = name,
            species = species,
            gender = gender,
            episodes = Json.decodeFromString<List<String>>(episodes),
            origin = origin
        ),
        selectedDay = selectedDate
    )
}
