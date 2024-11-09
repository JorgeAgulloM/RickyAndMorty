package org.example.rickyandmorty.ui.core.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems

@Composable
actual fun <T : Any> LazyRowTarget(
    modifier: Modifier,
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit,
    header: @Composable () -> Unit
) {
    LazyRow {
        item {
            header()
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(pagingItems.itemCount) { pos ->
            pagingItems[pos]?.let { item -> itemView(item) }
        }
    }
}