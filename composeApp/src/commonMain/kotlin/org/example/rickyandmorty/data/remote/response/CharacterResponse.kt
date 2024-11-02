package org.example.rickyandmorty.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.rickyandmorty.domain.model.CharacterModel

@Serializable
data class CharacterResponse(
    @SerialName("id") val id: Int,
    val status: String,
    val image: String,
    val name: String,
    val species: String,
    val gender: String,
    val origin: OriginResponse,
    @SerialName("episode") val episodes: List<String>
) {
    fun toDomain(): CharacterModel = CharacterModel(
        id = id,
        isAlive = status.lowercase() == "alive",
        image = image,
        name = name,
        species = species,
        gender = gender,
        origin = origin.name,
        episodes = episodes.map { it.substringAfterLast("/") }
    )
}
