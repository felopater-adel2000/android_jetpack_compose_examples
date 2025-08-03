package com.restart.jetpack_compose_examples.list

import com.restart.jetpack_compose_examples.ProductModel

interface IListRepository {

    suspend fun getList(): List<ProductModel>
}