package com.restart.jetpack_compose_examples

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductModel(
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("name")
    val name: String = ""
) : Parcelable