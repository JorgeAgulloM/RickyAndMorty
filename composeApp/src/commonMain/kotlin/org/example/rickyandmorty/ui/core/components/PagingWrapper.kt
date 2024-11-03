package org.example.rickyandmorty.ui.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems

@Composable
fun <T : Any> PagingWrapper(
    pagingTypeCustom: PagingTypeCustom,
    pagingItems: LazyPagingItems<T>,
    initialView: @Composable () -> Unit = {},
    emptyView: @Composable () -> Unit = {},
    extraItemsView: @Composable () -> Unit = {},
    itemView: @Composable (T) -> Unit = {},
    header: @Composable () -> Unit = {},
) {
    when {
        pagingItems.loadState.refresh is LoadState.Loading && pagingItems.itemCount == 0 -> initialView()
        pagingItems.loadState.refresh is LoadState.NotLoading && pagingItems.itemCount == 0 -> emptyView()
        else -> {

            when (pagingTypeCustom) {
                PagingTypeCustom.LazyRow -> LazyRow {
                    item {
                        header()
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    items(pagingItems.itemCount) { pos ->
                        pagingItems[pos]?.let { item -> itemView(item) }
                    }
                }

                PagingTypeCustom.LazyColumn -> LazyColumn {
                    item {
                        header()
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    items(pagingItems.itemCount) { pos ->
                        pagingItems[pos]?.let { item -> itemView(item) }
                    }
                }

                is PagingTypeCustom.LazyVerticalGrid -> LazyVerticalGrid(
                    columns = GridCells.Fixed(pagingTypeCustom.cells),
                    horizontalArrangement = Arrangement.spacedBy(pagingTypeCustom.spacing.dp),
                    verticalArrangement = Arrangement.spacedBy(pagingTypeCustom.spacing.dp)
                ) {
                    item(span = { GridItemSpan(2) }) { header() }
                    items(pagingItems.itemCount) { pos ->
                        pagingItems[pos]?.let { item -> itemView(item) }
                    }
                }

                is PagingTypeCustom.LazyHorizontalGrid -> LazyHorizontalGrid(
                    rows = GridCells.Fixed(pagingTypeCustom.cells),
                    horizontalArrangement = Arrangement.spacedBy(pagingTypeCustom.spacing.dp),
                    verticalArrangement = Arrangement.spacedBy(pagingTypeCustom.spacing.dp)
                ) {
                    item(span = { GridItemSpan(2) }) { header() }
                    items(pagingItems.itemCount) { pos ->
                        pagingItems[pos]?.let { item -> itemView(item) }
                    }
                }
            }

            if (pagingItems.loadState.append is LoadState.Loading) extraItemsView()
        }
    }
}

sealed interface PagingTypeCustom {
    data object LazyRow : PagingTypeCustom
    data object LazyColumn : PagingTypeCustom
    data class LazyVerticalGrid(val cells: Int, val spacing: Int) : PagingTypeCustom
    data class LazyHorizontalGrid(val cells: Int, val spacing: Int) : PagingTypeCustom
}
