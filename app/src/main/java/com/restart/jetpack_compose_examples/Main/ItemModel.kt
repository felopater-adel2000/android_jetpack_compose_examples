package com.restart.jetpack_compose_examples.Main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ItemModel(
    id: Long = System.currentTimeMillis(),
    text: String = "",
) {

    var id: Long by mutableStateOf(id)

    var text: String by mutableStateOf(text)

}