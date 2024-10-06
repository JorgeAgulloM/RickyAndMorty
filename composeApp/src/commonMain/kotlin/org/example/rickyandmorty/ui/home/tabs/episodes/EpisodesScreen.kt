package org.example.rickyandmorty.ui.home.tabs.episodes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun EpisodesScreen() {

    val viewModel = koinViewModel<EpisodesViewModel>()
    val state: EpisodesState by viewModel.state.collectAsStateWithLifecycle()


    Box(modifier = Modifier.fillMaxSize().background(Color.Yellow))
}

expect fun helloName(): String
