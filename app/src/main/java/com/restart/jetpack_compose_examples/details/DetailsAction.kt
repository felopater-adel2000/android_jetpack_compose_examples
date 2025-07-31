package com.restart.jetpack_compose_examples.details

import com.restart.jetpack_compose_examples.ProductModel

sealed interface DetailsAction {

    data class InitScreen(val data: ProductModel) : DetailsAction
}