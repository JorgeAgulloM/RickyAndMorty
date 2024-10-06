package org.example.rickyandmorty.data.remote.response

data class CharactersWrapperResponse(
    val info: InfoResponse,
    val result: List<CharacterResponse>
)
