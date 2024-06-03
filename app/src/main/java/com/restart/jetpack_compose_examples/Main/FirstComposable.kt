package com.restart.jetpack_compose_examples.Main

import android.util.Log

class FirstComposable (private val viewModel: MainViewModel) {


    init {
        Log.d("Felo", "init First Composable: ")
    }

    var text = ""
}