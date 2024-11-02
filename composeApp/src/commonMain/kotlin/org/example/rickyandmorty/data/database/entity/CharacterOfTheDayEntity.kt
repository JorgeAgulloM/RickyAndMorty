package org.example.rickyandmorty.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
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
    val species: String
) {
    fun toDomain(): CharacterOfTheDayModel = CharacterOfTheDayModel(
        characterModel = CharacterModel(id, isAlive, image, name, species),
        selectedDay = selectedDate
    )
}
