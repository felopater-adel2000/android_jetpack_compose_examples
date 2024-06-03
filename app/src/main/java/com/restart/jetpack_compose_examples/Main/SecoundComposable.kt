package com.restart.jetpack_compose_examples.Main

import android.util.Log

class SecoundComposable(private val viewModel: MainViewModel) {

    init {
        Log.d("Felo", "init Secound Composable: ")
    }

    var text = ""
}