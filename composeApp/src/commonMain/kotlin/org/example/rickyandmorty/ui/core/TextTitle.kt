package org.example.rickyandmorty.ui.core

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TextTitle(text: String) {
    Text(text = text.uppercase(), color = DefaultTextColor, fontWeight = FontWeight.Bold)
}