package org.example.rickyandmorty.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class EpisodesWrapperResponse(
    val info: InfoResponse,
    val results: List<EpisodesResponse>
)
