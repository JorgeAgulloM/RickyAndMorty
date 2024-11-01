package org.example.rickyandmorty.ui.home.tabs.episodes

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.example.rickyandmorty.domain.model.EpisodeModel

data class EpisodesState(
    val episodes: Flow<PagingData<EpisodeModel>> = emptyFlow(),
    val playVideo: String = ""
)
