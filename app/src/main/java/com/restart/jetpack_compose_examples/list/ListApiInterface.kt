package com.restart.jetpack_compose_examples.list

import com.restart.jetpack_compose_examples.ProductModel
import retrofit2.http.GET

interface ListApiInterface {
    @GET("products")
    suspend fun getList(): List<ProductModel>
}