package org.example.rickyandmorty.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.rickyandmorty.domain.model.CharacterModel

class GetRandomCharacter(private val repository: Repository) {
    suspend operator fun invoke(): CharacterModel = (1..826).random().toString().let { id ->
        repository.getCharacterDB()
        repository.getSingleCharacter(id)
    }

    private fun getCurrentDayOfTheYear(): String =
        Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .let { localTime ->
                "${localTime.dayOfYear}${localTime.year}"
            }

}
