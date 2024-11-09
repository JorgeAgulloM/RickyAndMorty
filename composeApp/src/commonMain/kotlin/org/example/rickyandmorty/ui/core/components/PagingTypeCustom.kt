package org.example.rickyandmorty.ui.core.components

sealed class PagingTypeCustom {
    data object LazyRow : PagingTypeCustom()
    data object LazyColumn : PagingTypeCustom()
    data class LazyVerticalGrid(val cells: Int, val spacing: Int) : PagingTypeCustom()
    data class LazyHorizontalGrid(val cells: Int, val spacing: Int) : PagingTypeCustom()
}