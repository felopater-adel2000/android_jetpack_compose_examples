package com.restart.jetpack_compose_examples

import android.app.Application
import com.google.gson.GsonBuilder
import com.restart.jetpack_compose_examples.details.DetailsViewModel
import com.restart.jetpack_compose_examples.list.IListRepository
import com.restart.jetpack_compose_examples.list.ListApiInterface
import com.restart.jetpack_compose_examples.list.ListRepository
import com.restart.jetpack_compose_examples.list.ListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    single<ListApiInterface> {

        Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(get())
            .build()
            .create(ListApiInterface::class.java)

    }

    single<IListRepository> { ListRepository(get()) }

    viewModelOf(::ListViewModel)
    viewModelOf(::DetailsViewModel)

}