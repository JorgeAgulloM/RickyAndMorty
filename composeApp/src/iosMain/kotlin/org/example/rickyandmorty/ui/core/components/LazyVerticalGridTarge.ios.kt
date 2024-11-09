package org.example.rickyandmorty.ui.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems

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
    LazyVerticalGrid(
        columns = GridCells.Fixed(cells),
        horizontalArrangement = Arrangement.spacedBy(spacing.dp),
        verticalArrangement = Arrangement.spacedBy(spacing.dp)
    ) {
        item(span = { GridItemSpan(2) }) { header() }
        items(pagingItems.itemCount) { pos ->
            pagingItems[pos]?.let { item -> itemView(item) }
        }
    }
}