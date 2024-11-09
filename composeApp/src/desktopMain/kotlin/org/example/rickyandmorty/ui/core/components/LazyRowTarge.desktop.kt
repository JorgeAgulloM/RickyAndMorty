package org.example.rickyandmorty.ui.core.components

import androidx.compose.foundation.HorizontalScrollbar
import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems

@Composable
actual fun <T : Any> LazyRowTarget(
    modifier: Modifier,
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit,
    header: @Composable () -> Unit
) {
    val lazyState = rememberLazyListState()
    Column {
        LazyRow(state = lazyState) {
            item {
                header()
                Spacer(modifier = modifier.height(16.dp))
            }
            items(pagingItems.itemCount) { pos ->
                pagingItems[pos]?.let { item -> itemView(item) }
            }
        }
        Spacer(modifier = modifier.height(2.dp))
        HorizontalScrollbar(adapter = rememberScrollbarAdapter(lazyState), style = LocalScrollbarStyle.current.copy(
            unhoverColor = Green.copy(alpha = 0.4f),
            hoverColor = Green
        ))
    }
}