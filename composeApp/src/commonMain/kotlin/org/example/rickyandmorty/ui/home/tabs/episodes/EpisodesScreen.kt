package org.example.rickyandmorty.ui.home.tabs.episodes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cash.paging.compose.collectAsLazyPagingItems
import org.example.rickyandmorty.domain.model.EpisodeModel
import org.example.rickyandmorty.domain.model.SeasonEpisode
import org.example.rickyandmorty.domain.model.SeasonEpisode.SEASON_1
import org.example.rickyandmorty.domain.model.SeasonEpisode.SEASON_2
import org.example.rickyandmorty.domain.model.SeasonEpisode.SEASON_3
import org.example.rickyandmorty.domain.model.SeasonEpisode.SEASON_4
import org.example.rickyandmorty.domain.model.SeasonEpisode.SEASON_5
import org.example.rickyandmorty.domain.model.SeasonEpisode.SEASON_6
import org.example.rickyandmorty.domain.model.SeasonEpisode.SEASON_7
import org.example.rickyandmorty.domain.model.SeasonEpisode.UNKNOWN
import org.example.rickyandmorty.ui.core.components.PagingLoadingState
import org.example.rickyandmorty.ui.core.components.PagingTypeCustom
import org.example.rickyandmorty.ui.core.components.PagingWrapper
import org.example.rickyandmorty.ui.core.components.VideoPlayer
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import rickyandmorty.composeapp.generated.resources.Res
import rickyandmorty.composeapp.generated.resources.portal
import rickyandmorty.composeapp.generated.resources.rickface
import rickyandmorty.composeapp.generated.resources.season1
import rickyandmorty.composeapp.generated.resources.season2
import rickyandmorty.composeapp.generated.resources.season3
import rickyandmorty.composeapp.generated.resources.season4
import rickyandmorty.composeapp.generated.resources.season5
import rickyandmorty.composeapp.generated.resources.season6
import rickyandmorty.composeapp.generated.resources.season7

@OptIn(KoinExperimentalAPI::class)
@Composable
fun EpisodesScreen() {

    val viewModel = koinViewModel<EpisodesViewModel>()
    val state: EpisodesState by viewModel.state.collectAsStateWithLifecycle()
    val episodes = state.episodes.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {
        PagingWrapper(
            pagingTypeCustom = PagingTypeCustom.LazyRow,
            pagingItems = episodes,
            initialView = { PagingLoadingState() },
            itemView = {
                EpisodeItemList(it) { url ->
                    viewModel.onPlaySelected(url)
                }
            }
        )

        EpisodePlayer(state.playVideo) { viewModel.onCloseVideo() }
    }
}

@Composable
private fun EpisodePlayer(
    playVideo: String,
    onCloseVideo: () -> Unit
) {
    AnimatedVisibility (playVideo.isNotBlank()) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth()
                .height(250.dp)
                .padding(16.dp)
                .border(3.dp, Green, CardDefaults.elevatedShape)
        ) {
            Box(modifier = Modifier.background(Black)) {
                Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
                    VideoPlayer(Modifier.fillMaxWidth().height(200.dp), playVideo)
                }
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(Res.drawable.portal),
                        contentDescription = "Close Video",
                        modifier = Modifier.padding(8.dp).size(40.dp).clickable {
                            onCloseVideo()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EpisodeItemList(episode: EpisodeModel, onEpisodeSelected: (String) -> Unit) {
    Column(
        modifier = Modifier.width(120.dp)
            .padding(horizontal = 8.dp)
            .clickable {
                onEpisodeSelected(episode.videoUrl)
            }
    ) {
        Image(
            modifier = Modifier.height(200.dp).fillMaxWidth(),
            contentDescription = episode.name,
            contentScale = ContentScale.Inside,
            painter = painterResource(getSeasonImage(episode.season))
        )
    }
}

fun getSeasonImage(season: SeasonEpisode): DrawableResource {
    return when (season) {
        SEASON_1 -> Res.drawable.season1
        SEASON_2 -> Res.drawable.season2
        SEASON_3 -> Res.drawable.season3
        SEASON_4 -> Res.drawable.season4
        SEASON_5 -> Res.drawable.season5
        SEASON_6 -> Res.drawable.season6
        SEASON_7 -> Res.drawable.season7
        UNKNOWN -> Res.drawable.rickface
    }
}

expect fun helloName(): String
