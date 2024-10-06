package org.example.rickyandmorty.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.rickyandmorty.domain.model.CharacterModel
import org.example.rickyandmorty.domain.model.CharacterOfTheDayModel

class GetRandomCharacter(private val repository: Repository) {
    suspend operator fun invoke(): CharacterModel {
        val characterOfTheDay: CharacterOfTheDayModel? = repository.getCharacterDB()
        val selectedDay = getCurrentDayOfTheYear()
        return if (characterOfTheDay != null && characterOfTheDay.selectedDay == selectedDay) {
            characterOfTheDay.characterModel
        } else {
            generateCharacterModel().let { characterModel ->
                repository.sevaCharacterDB(CharacterOfTheDayModel(characterModel, selectedDay))
                characterModel
            }
        }
    }

    private suspend fun generateCharacterModel(): CharacterModel =
        (1..826).random().toString().let { id -> repository.getSingleCharacter(id) }

    private fun getCurrentDayOfTheYear(): String =
        Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .let { localTime -> "${localTime.dayOfYear}${localTime.year}" }

}
