package com.restart.jetpack_compose_examples.list

import com.restart.jetpack_compose_examples.ProductModel

sealed interface ListAction {

    object LoadData : ListAction

    data class OnProductClick(val product: ProductModel) : ListAction

    object SetToken : ListAction
}