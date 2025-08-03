package com.restart.jetpack_compose_examples.list

import com.restart.jetpack_compose_examples.ProductModel

class ListRepository(private val apiInterface: ListApiInterface) : IListRepository {
    override suspend fun getList(): List<ProductModel> {
        return apiInterface.getList()
    }
}