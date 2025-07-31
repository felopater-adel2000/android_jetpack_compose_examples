package com.restart.jetpack_compose_examples

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _screenDirectionEvent = MutableSharedFlow<ScreenDirection?>()
    val screenDirectionEvent = _screenDirectionEvent.asSharedFlow()

    fun emitScreenDirectionEvent(direction: ScreenDirection) {
        viewModelScope.launch { _screenDirectionEvent.emit(direction) }
    }
}