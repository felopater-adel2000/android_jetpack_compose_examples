package com.restart.jetpack_compose_examples.list

import com.restart.jetpack_compose_examples.ProductModel

class ListRepository : IListRepository {
    override suspend fun getList(): List<ProductModel> {
        return List(100) { index ->
            ProductModel(
                id = index,
                name = "Product $index",
            )
        }
    }
}