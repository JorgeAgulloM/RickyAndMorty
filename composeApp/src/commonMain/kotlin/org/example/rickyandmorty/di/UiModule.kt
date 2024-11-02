package org.example.rickyandmorty.di

import org.example.rickyandmorty.ui.detail.CharacterViewModel
import org.example.rickyandmorty.ui.home.tabs.characters.CharactersViewModel
import org.example.rickyandmorty.ui.home.tabs.episodes.EpisodesViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::EpisodesViewModel)
    viewModelOf(::CharactersViewModel)
    viewModelOf(::CharacterViewModel)
}
