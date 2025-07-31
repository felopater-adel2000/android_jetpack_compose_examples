package com.restart.jetpack_compose_examples.details

import androidx.lifecycle.ViewModel
import com.restart.jetpack_compose_examples.ProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DetailsViewModel : ViewModel() {

    private val _viewState = MutableStateFlow(DetailsViewState())
    val viewState = _viewState.asStateFlow()

    fun onAction(action: DetailsAction) {
        when (action) {
            is DetailsAction.InitScreen -> initScreen(action.data)
        }
    }

    private fun initScreen(product: ProductModel) {
        _viewState.update { state ->
            state.copy(
                product = product,
            )
        }
    }
}