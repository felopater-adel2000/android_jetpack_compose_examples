package com.restart.jetpack_compose_examples

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModels : ViewModel()
{
    val state = MutableStateFlow(MainState())

    fun updateCounter()
    {
        state.value = state.value.copy(counter = state.value.counter.plus(1))
    }

    fun updateSlider(value: Float)
    {
        state.value = state.value.copy(sliderValue = value.toInt())
    }
}