package org.example.rickyandmorty.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.example.rickyandmorty.domain.model.CharacterModel
import org.example.rickyandmorty.domain.model.EpisodeModel
import org.example.rickyandmorty.ui.core.BackgroundPrimaryColor
import org.example.rickyandmorty.ui.core.BackgroundSecondaryColor
import org.example.rickyandmorty.ui.core.BackgroundTertiaryColor
import org.example.rickyandmorty.ui.core.DefaultTextColor
import org.example.rickyandmorty.ui.core.Green
import org.example.rickyandmorty.ui.core.Pink
import org.example.rickyandmorty.ui.core.TextTitle
import org.example.rickyandmorty.ui.core.extensions.aliveBorder
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf
import rickyandmorty.composeapp.generated.resources.Res
import rickyandmorty.composeapp.generated.resources.space

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharacterDetailScreen(modifier: Modifier = Modifier, characterModel: CharacterModel) {
    val viewModel =
        koinViewModel<CharacterViewModel>(parameters = { parametersOf(characterModel) })

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimaryColor)
            .verticalScroll(scrollState)
    ) {
        MainHeader(characterModel = state.characterModel)
        Spacer(modifier = modifier.height(16.dp))
        Column(
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
                .background(BackgroundSecondaryColor)
        ) {
            CharacterInfo(modifier, state.characterModel)
            CharacterEpisodes(modifier, state.episodes)
        }
    }

    LaunchedEffect(state.characterModel.episodes.isNotEmpty()) {
        viewModel.getEpisodesForCharacter(state.characterModel.episodes)
    }
}

@Composable
fun CharacterEpisodes(modifier: Modifier, episodes: List<EpisodeModel>?) {
    ElevatedCard(
        modifier = modifier.padding(horizontal = 16.dp).fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = BackgroundTertiaryColor)
    ) {
        Box(modifier = modifier.padding(16.dp), contentAlignment = Alignment.Center) {
            if (episodes == null) {
                CircularProgressIndicator(color = Green)
            } else {
                Column {
                    TextTitle(text = "Episode List")
                    Spacer(modifier = modifier.height(6.dp))

                    episodes.forEach { episode ->
                        EpisodeItem(modifier, episode)
                        Spacer(modifier = modifier.height(4.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun EpisodeItem(modifier: Modifier, episode: EpisodeModel) {
    Text(episode.name, color = Green, fontWeight = FontWeight.Bold)
    Text(episode.episode, color = DefaultTextColor)
}

@Composable
fun CharacterInfo(modifier: Modifier, characterModel: CharacterModel) {
    ElevatedCard(
        modifier = modifier.padding(16.dp).fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = BackgroundTertiaryColor)
    ) {
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            TextTitle(text = "ABOUT THE CHARACTER")
            Spacer(modifier = modifier.height(6.dp))
            InfoDetail("Origin:", characterModel.origin)
            Spacer(modifier = modifier.height(2.dp))
            InfoDetail("Gender:", characterModel.gender)
        }
    }
}

@Composable
fun InfoDetail(title: String, detail: String) {
    Row {
        Text(text = title, color = DefaultTextColor, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = detail, color = Green)
    }
}

@Composable
fun MainHeader(modifier: Modifier = Modifier, characterModel: CharacterModel) {
    Box(modifier = modifier.fillMaxWidth().height(300.dp)) {
        Image(
            painter = painterResource(Res.drawable.space),
            contentDescription = "Background header",
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        CharacterHeader(modifier, characterModel = characterModel)
    }
}

@Composable
fun CharacterHeader(modifier: Modifier = Modifier, characterModel: CharacterModel) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = characterModel.name,
                color = Pink,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Specie: ${characterModel.species}", color = Color.Black)
        }
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))
            Box(contentAlignment = Alignment.TopCenter) {
                Box(
                    modifier = modifier
                        .size(205.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = characterModel.image,
                        contentDescription = characterModel.name,
                        modifier = modifier
                            .size(190.dp)
                            .clip(CircleShape)
                            .aliveBorder(isAlive = characterModel.isAlive),
                        contentScale = ContentScale.Crop
                    )
                }

                val aliveName = if (characterModel.isAlive) "ALIVE" else "DEAD"
                val aliveColor = if (characterModel.isAlive) Green else Color.Red

                Text(
                    text = aliveName,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .clip(RoundedCornerShape(30))
                        .background(aliveColor)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
            Spacer(modifier = modifier.weight(1f))
        }
    }
}
