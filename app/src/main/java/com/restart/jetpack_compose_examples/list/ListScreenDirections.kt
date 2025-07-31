package com.restart.jetpack_compose_examples.list

import android.util.Log
import androidx.navigation.NavController
import com.restart.jetpack_compose_examples.ProductModel
import com.restart.jetpack_compose_examples.ScreenDirection

sealed interface ListScreenDirections : ScreenDirection {

    class NavigateToDetailsFragment(val product: ProductModel) : ListScreenDirections {
        override fun execute(navController: NavController) {
            Log.d("TAG", "execute: ")
            navController.navigate(
                ListFragmentDirections.actionListFragmentToDetailsFragment(
                    product
                )
            )
        }

    }
}