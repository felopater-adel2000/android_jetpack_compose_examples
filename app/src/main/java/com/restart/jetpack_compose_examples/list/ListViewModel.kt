package com.restart.jetpack_compose_examples.list

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.lifecycle.viewModelScope
import com.restart.jetpack_compose_examples.BaseViewModel
import com.restart.jetpack_compose_examples.ProductModel
import com.restart.jetpack_compose_examples.datastore.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListViewModel(
    val dataStore: DataStore<SessionManager>,
    private val repo: IListRepository
) : BaseViewModel() {


    private val _viewState = MutableStateFlow(ListViewState())
    val viewState = _viewState.asStateFlow()


    fun onAction(action: ListAction) {
        when (action) {
            ListAction.LoadData -> getData()

            is ListAction.OnProductClick -> navigateToDatilsScreen(action.product)

            is ListAction.SetToken -> setRandomToken()
        }
    }

    private fun navigateToDatilsScreen(product: ProductModel) {
        Log.d("TAG", "navigateToDatilsScreen: ")
        emitScreenDirectionEvent(
            ListScreenDirections.NavigateToDetailsFragment(product)
        )
    }

    private fun setRandomToken() = viewModelScope.launch {
        dataStore.updateData { it.copy(token = System.currentTimeMillis().toString()) }
    }

    private fun getData() {
        Log.d("TAGFelo", "getData: ")
        viewModelScope.launch {
            runCatching {
                repo.getList()
            }.onSuccess { list ->
                _viewState.update { state ->
                    state.copy(
                        products = list,
                    )
                }
            }.onFailure {

            }
        }
    }


}