package com.restart.jetpack_compose_examples

import android.app.Application
import android.util.Log

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("TestApplication", "onCreate: ")
    }
}