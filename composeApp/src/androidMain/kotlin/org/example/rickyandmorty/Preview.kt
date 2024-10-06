package org.example.rickyandmorty

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.rickyandmorty.domain.model.CharacterModel
import org.example.rickyandmorty.ui.home.tabs.characters.CharacterOfTheDay

@Preview
@Composable
fun Preview() {
    CharacterOfTheDay(CharacterModel(id = 3, image = "", isAlive = true, name = "Pepe"))
}