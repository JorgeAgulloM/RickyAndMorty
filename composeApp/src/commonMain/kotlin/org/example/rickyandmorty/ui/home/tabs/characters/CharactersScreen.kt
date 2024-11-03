package org.example.rickyandmorty.ui.home.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import org.example.rickyandmorty.domain.model.CharacterModel
import org.example.rickyandmorty.ui.core.BackgroundPrimaryColor
import org.example.rickyandmorty.ui.core.DefaultTextColor
import org.example.rickyandmorty.ui.core.Green
import org.example.rickyandmorty.ui.core.components.PagingLoadingState
import org.example.rickyandmorty.ui.core.components.PagingTypeCustom
import org.example.rickyandmorty.ui.core.components.PagingWrapper
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

    Column(
        modifier = Modifier.fillMaxSize().background(BackgroundPrimaryColor)
            .padding(horizontal = 16.dp),
    ) {
        PagingWrapper(
            pagingTypeCustom = PagingTypeCustom.LazyVerticalGrid(2, 16),
            pagingItems = characters,
            initialView = { PagingLoadingState() },
            emptyView = { Text(text = "No hay personajes :(") },
            itemView = { characterModel ->
                CharacterItemList(characterModel) { character ->
                    navigateToDetail(character)
                }
            },
            header = {
                Column {
                    Text(
                        text = "Characters",
                        color = DefaultTextColor,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    CharacterOfTheDay(characterOfTheDay)
                }
            }
        )
    }
}

@Composable
fun CharacterItemList(characterModel: CharacterModel, onItemSelected: (CharacterModel) -> Unit) {
    Box(
        modifier = Modifier.clip(RoundedCornerShape(24))
            .border(
                2.dp,
                Green,
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
    Card(modifier = Modifier.fillMaxWidth().height(300.dp), shape = RoundedCornerShape(12)) {
        if (characterModel == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Green)
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