package org.example.rickyandmorty.ui.core

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val BackgroundPrimaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) PrimaryBlack else PrimaryWhite

val BackgroundSecondaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) SecondaryBlack else SecondaryWhite

val BackgroundTertiaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) TertiaryBlack else TertiaryWhite

val BackgroundPlaceholderColor
    @Composable
    get() = if (isSystemInDarkTheme()) TertiaryBlack else SecondaryWhite

val DefaultTextColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.Black


// Generic colors
val Pink = Color(0xFFFF577D)
val Green = Color(0xFF5CCF92)

// LightColors
val PrimaryWhite = Color(0xFFFFFFFF)
val SecondaryWhite = Color(0xFFEAE8EF)
val TertiaryWhite = Color(0xFFFAFAFA)

// DarkColors
val PrimaryBlack = Color(0xFF000000)
val SecondaryBlack = Color(0xFF302f2f)
val TertiaryBlack = Color(0xFF464646)
