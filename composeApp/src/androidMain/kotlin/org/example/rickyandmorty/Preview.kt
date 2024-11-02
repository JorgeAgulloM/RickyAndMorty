package org.example.rickyandmorty

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.rickyandmorty.domain.model.CharacterModel
import org.example.rickyandmorty.ui.home.tabs.characters.CharacterOfTheDay

@Preview
@Composable
fun Preview() {
    CharacterOfTheDay(CharacterModel(
        id = 3,
        isAlive = true,
        image = "",
        name = "Pepe",
        species = "Humano",
        gender = "Masculino",
        origin = "Tierra",
        episodes = listOf("1", "2", "3")
    ))
}