package org.example.rickyandmorty

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.PointerMatcher
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun UtilsScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Spacer(modifier.size(20.dp))
        MouseListeners(modifier)
        Spacer(modifier.size(20.dp))
        KeyListener(modifier)
        Spacer(modifier.size(20.dp))
        DragItem()
        Spacer(modifier.size(20.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MouseListeners(modifier: Modifier = Modifier) {
    var click by remember { mutableStateOf("Vacio") }
    Column {
        TooltipArea(
            tooltip = {
                Text(
                    text = "Pulsa para ver el tipo de click",
                    color = Color.White,
                    modifier = modifier
                        .background(Color.Gray, shape = RoundedCornerShape(4.dp))
                        .padding(4.dp)
                        .shadow(elevation = 4.dp, shape = RoundedCornerShape(4.dp))
                )
            },
            delayMillis = 1500,
            tooltipPlacement = TooltipPlacement.ComponentRect(
                alignment = Alignment.TopStart
            )
        ) {
            Box(modifier = modifier
                .size(300.dp)
                .background(Color.Red)
                .combinedClickable(
                    onClick = { click = "normal" },
                    onDoubleClick = { click = "doble" },
                    onLongClick = { click = "largo" }
                )
            )
        }

        Text("Tipo de click: $click")
    }
}

@Composable
private fun KeyListener(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    Box(modifier = modifier.onPreviewKeyEvent {
        if (it.key == Key.Spacebar) {
            text = "Yorch"
            true
        } else {
            false
        }
    }) {
        TextField(text, onValueChange = { text = it })
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DragItem(modifier: Modifier = Modifier) {
    var currentOffset by remember { mutableStateOf(Offset(0f, 0f)) }

    Box(modifier = modifier
        .offset { IntOffset(currentOffset.x.toInt(), currentOffset.y.toInt()) }
        .size(200.dp)
        .background(Color.Green)
        .onDrag(
            matcher = PointerMatcher.mouse(PointerButton.Primary),
            onDragStart = { print("Empieza el drag") },
            onDragEnd = { print("Finaliza el drag") },
            onDrag = { currentOffset += it }
        )
    )
}
