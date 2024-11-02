package org.example.rickyandmorty.ui.home.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import org.example.rickyandmorty.domain.model.CharacterModel
import org.example.rickyandmorty.ui.core.extensions.vertical
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import rickyandmorty.composeapp.generated.resources.Res
import rickyandmorty.composeapp.generated.resources.rickface

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreen(navigateToDetail: (CharacterModel) -> Unit) {
    val viewModel = koinViewModel<CharactersViewModel>()
    val state: CharactersState by viewModel.state.collectAsStateWithLifecycle()
    val characters = state.characters.collectAsLazyPagingItems()

    CharactersGridList(characters, state.characterOfTheDay, navigateToDetail)
}

@Composable
fun CharactersGridList(
    characters: LazyPagingItems<CharacterModel>,
    characterOfTheDay: CharacterModel?,
    navigateToDetail: (CharacterModel) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item(span = { GridItemSpan(2) }) {
            Column {
                Text(text = "Characters", color = Color.Black, fontSize = 24.sp)
                CharacterOfTheDay(characterOfTheDay)
            }
        }

        when {
            // Loading Api
            characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
                item(span = { GridItemSpan(2) }) {
                    Box(modifier = Modifier.fillMaxHeight().height(100.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(Modifier.size(64.dp), color = Color.Green)
                    }
                }
            }

            // Empty Api
            characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
                item { Text(text = "No hay personajes :(") }
            }

            else -> {
                // Review Items
                items(characters.itemCount) { pos ->
                    characters[pos]?.let { characterModel ->
                        CharacterItemList(characterModel) { character ->
                            navigateToDetail(character)
                        }
                    }
                }

                //Waiting Data
                if (characters.loadState.append is LoadState.Loading) {
                    item(span = { GridItemSpan(2) }) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(Modifier.size(64.dp), color = Color.Red)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItemList(characterModel: CharacterModel, onItemSelected: (CharacterModel) -> Unit) {
    Box(
        modifier = Modifier.clip(RoundedCornerShape(24))
            .border(
                2.dp,
                Color.Green,
                shape = RoundedCornerShape(0, 24, 0, 24)
            ).fillMaxWidth()
            .height(150.dp)
            .clickable {
                onItemSelected(characterModel)
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = characterModel.image,
            contentDescription = "Character of the day",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            placeholder = painterResource(resource = Res.drawable.rickface)
        )
        Box(
            modifier = Modifier.fillMaxWidth().height(60.dp).background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color.Black.copy(0f),
                        Color.Black.copy(0.6f),
                        Color.Black.copy(1f),
                    )
                )
            ),
            contentAlignment = Alignment.Center
        ) {
            Text(text = characterModel.name, fontSize = 20.sp, color = Color.White)
        }
    }
}

@Composable
fun CharacterOfTheDay(characterModel: CharacterModel? = null) {
    Card(modifier = Modifier.fillMaxWidth().height(400.dp), shape = RoundedCornerShape(12)) {
        if (characterModel == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Green)
            }
        } else {
            Box(contentAlignment = Alignment.BottomStart) {
                AsyncImage(
                    model = characterModel.image,
                    contentDescription = "Character of the day",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier.fillMaxSize().background(
                        Brush.horizontalGradient(
                            0f to Color.Black.copy(alpha = 0.9f),
                            0.4f to Color.White.copy(alpha = 0f)
                        )
                    )
                )
                Text(
                    text = characterModel.name,
                    fontSize = 40.sp,
                    maxLines = 1,
                    minLines = 1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                        .fillMaxHeight()
                        .vertical()
                        .rotate(-90f)
                )
            }
        }
    }
}