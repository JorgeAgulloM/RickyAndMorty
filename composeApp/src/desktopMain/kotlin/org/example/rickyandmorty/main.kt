package org.example.rickyandmorty

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.rickyandmorty.di.initKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Rick and Morty App"
    ) {
        initKoin()
        App()
    }
}