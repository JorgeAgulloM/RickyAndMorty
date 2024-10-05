package org.example.rickyandmorty

import androidx.compose.ui.window.ComposeUIViewController
import org.example.rickyandmorty.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }