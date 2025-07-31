package com.restart.jetpack_compose_examples.list

import com.restart.jetpack_compose_examples.ProductModel

data class ListViewState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val products: List<ProductModel> = listOf()
)