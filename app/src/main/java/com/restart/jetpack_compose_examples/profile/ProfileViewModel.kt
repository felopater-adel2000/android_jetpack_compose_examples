package com.restart.jetpack_compose_examples.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import com.restart.jetpack_compose_examples.Main.MainRepository

class ProfileViewModel(private val repo: MainRepository) : ViewModel()
{
    init {
        Log.d("Felo", "init ProfileViewModel: ")
    }
}