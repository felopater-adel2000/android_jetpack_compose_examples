package com.restart.jetpack_compose_examples

import com.restart.jetpack_compose_examples.ui.theme.MyEnum
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@InternalSerializationApi
@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class Person (
    @SerialName("person_name")
    val name: String,
    @EncodeDefault
    val email: String = "",
    @EncodeDefault
    val myEnum: Int = MyEnum.ONE.value,

    @EncodeDefault
    val cars: List<Car> = listOf(
        Car(1, "Toyota"),
        Car(2, "Honda")
    )
)

@InternalSerializationApi @Serializable
data class Car(
    val id: Int,
    val name: String,
)