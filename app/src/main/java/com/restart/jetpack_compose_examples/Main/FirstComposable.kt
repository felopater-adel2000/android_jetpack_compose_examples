package com.restart.jetpack_compose_examples.Main

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirstComposable (
    private val viewModel: MainViewModel,
    private val coroutineScope: CoroutineScope
) {


    init {
        Log.d("Felo", "init First Composable: ")
    }

    var text = ""

    suspend fun getMainData() {
        withContext(Dispatchers.Main){

        }
    }
}