package com.restart.jetpack_compose_examples

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductModel(
    val id: Int = 0,
    val name: String = ""
) : Parcelable