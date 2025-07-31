package com.restart.jetpack_compose_examples

import android.app.Application
import com.restart.jetpack_compose_examples.details.DetailsViewModel
import com.restart.jetpack_compose_examples.list.ListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(
                viewModelModule
            )
        }
    }
}

val viewModelModule = module {
    println("Start Koin Module")

    viewModelOf(::ListViewModel)
    viewModelOf(::DetailsViewModel)

}