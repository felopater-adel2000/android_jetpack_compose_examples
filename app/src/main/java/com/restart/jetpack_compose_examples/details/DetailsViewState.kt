package com.restart.jetpack_compose_examples.details

import com.restart.jetpack_compose_examples.ProductModel

data class DetailsViewState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val product: ProductModel? = null
)