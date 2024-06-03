package com.restart.jetpack_compose_examples.Main

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel(private val repo: MainRepository) : ViewModel() {

    init {
        Log.d("Felo", "init ViewModel ")
    }
    fun getMainData() = repo.getMainDate()

}