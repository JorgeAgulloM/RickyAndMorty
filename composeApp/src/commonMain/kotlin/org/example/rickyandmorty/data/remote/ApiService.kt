package org.example.rickyandmorty.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.rickyandmorty.data.remote.response.CharacterResponse
import org.example.rickyandmorty.data.remote.response.CharactersWrapperResponse

private const val CHARACTER_ENDPOINT = "/api/character/"
private const val PAGE = "page"

class ApiService(private val client: HttpClient) {

    suspend fun getSingleCharacter(id: String): CharacterResponse {
        return client.get("$CHARACTER_ENDPOINT$id").body()
    }

    suspend fun getAllCharacters(page: Int): CharactersWrapperResponse {
        return client.get(CHARACTER_ENDPOINT) { parameter(PAGE, page) }.body()
    }

}