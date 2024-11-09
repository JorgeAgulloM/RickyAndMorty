package org.example.rickyandmorty.ui.core.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.paging.compose.LazyPagingItems

@Composable
expect fun <T : Any> LazyRowTarget(
    modifier: Modifier,
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit,
    header: @Composable () -> Unit
)
