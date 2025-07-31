package com.restart.jetpack_compose_examples

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController

interface ScreenDirection {

    fun execute(navController: NavController)

}


@Composable
fun ScreenDirectionEventHandler(
    viewModel: BaseViewModel,
    navController: NavController,
) {
    LaunchedEffect(viewModel.screenDirectionEvent) {
        Log.d("TAG", "ScreenDirectionEventHandler: ")
        viewModel.screenDirectionEvent.collect { direction ->
            direction?.let { direction.execute(navController) }
        }
    }
}