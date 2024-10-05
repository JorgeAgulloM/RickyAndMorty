package org.example.rickyandmorty.data.remote.response

import kotlinx.serialization.Serializable
import org.example.rickyandmorty.domain.model.CharacterModel

@Serializable
data class CharacterResponse(
    val id: Int,
    val status: String,
    val image: String
) {
    fun toDomain(): CharacterModel = CharacterModel(
        id = id,
        isAlive = status.lowercase() == "alive",
        image = image
    )
}
