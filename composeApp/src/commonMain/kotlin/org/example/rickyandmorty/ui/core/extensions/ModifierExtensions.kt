package org.example.rickyandmorty.ui.core.extensions

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import org.example.rickyandmorty.ui.core.Green

fun Modifier.vertical() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(placeable.height, placeable.width) {
        placeable.place(
            x = -(placeable.width / 2 - placeable.height / 2),
            y = -(placeable.height / 2 - placeable.width / 2)
        )
    }
}

fun Modifier.aliveBorder(isAlive: Boolean): Modifier =
    border(4.dp, if (isAlive) Green else Color.Red, CircleShape)