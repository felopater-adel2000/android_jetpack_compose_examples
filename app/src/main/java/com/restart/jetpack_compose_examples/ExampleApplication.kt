package com.restart.jetpack_compose_examples

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.annotation.KoinApplication
import org.koin.ksp.generated.startKoin


class ExampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        /*startKoin {
            modules(MyKoinApplication().modules)
        }*/

        MyKoinApplication().startKoin {
            androidLogger()
            androidContext(this@ExampleApplication)
        }

    }
}

@KoinApplication(modules = [AppModule::class])
class MyKoinApplication




