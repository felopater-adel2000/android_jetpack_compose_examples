package com.restart.jetpack_compose_examples

import android.util.Log
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

class DataRepository : IDataRepository {
    override fun getData(): String {
        return "Hello from DataRepository!"
    }
}

interface IDataRepository {
    fun getData(): String
}

/*val dataModule = module {
    single<IDataRepository> { DataRepository() }
}*/

@Factory
class SettingsRepository() {
    init {
        Log.d("Koin", ": Init SettingsRepository")
    }

    fun getSettings(): String {
        return "Hello from SettingsRepository!"
    }
}

/*
val settingsModule = module {
    factory { SettingsRepository() }
}*/

@Module
@ComponentScan("com.restart.jetpack_compose_examples")
class AppModule {
    @Single
    fun provideDataRepository(): IDataRepository {
        return DataRepository()
    }
}