package com.restart.jetpack_compose_examples

import android.app.Application
import com.restart.jetpack_compose_examples.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(
                appModule
            )
        }
    }
}