package com.restart.jetpack_compose_examples

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class CompositionViewModel : ViewModel()
{
    val oneTextField = MutableStateFlow("")

    val twoTextField = MutableStateFlow("")

    fun updateOneTextField(oneText: String)
    {
        oneTextField.update { oneText }
    }


    fun updateTwoTextField(twoText: String)
    {
        twoTextField.update { twoText }
    }
}