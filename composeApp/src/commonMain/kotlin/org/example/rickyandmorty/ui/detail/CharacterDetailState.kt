package org.example.rickyandmorty.ui.detail

import org.example.rickyandmorty.domain.model.CharacterModel
import org.example.rickyandmorty.domain.model.EpisodeModel

data class CharacterDetailState(
    val characterModel: CharacterModel,
    val episodes: List<EpisodeModel>? = null
)
