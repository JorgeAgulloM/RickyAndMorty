package org.example.rickyandmorty.ui.core.components

import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems

@Composable
actual fun <T : Any> LazyVerticalGridTarget(
    modifier: Modifier,
    cells: Int,
    spacing: Int,
    pagingTypeCustom: PagingTypeCustom,
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit,
    header: @Composable () -> Unit
) {
    Box(contentAlignment = Alignment.TopEnd) {
        val lazyState = rememberLazyGridState()
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            state = lazyState,
            horizontalArrangement = Arrangement.spacedBy(spacing.dp),
            verticalArrangement = Arrangement.spacedBy(spacing.dp)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) { header() }
            items(pagingItems.itemCount) { pos ->
                pagingItems[pos]?.let { item -> itemView(item) }
            }
        }
        VerticalScrollbar(
            adapter = rememberScrollbarAdapter(lazyState),
            style = LocalScrollbarStyle.current.copy(
                unhoverColor = Green.copy(alpha = 0.4f),
                hoverColor = Green,
                thickness = 10.dp
            ),
            modifier = modifier.offset { IntOffset(16,0) }
        )
    }
}
