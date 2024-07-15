package com.restart.jetpack_compose_examples.di

import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import com.restart.jetpack_compose_examples.BaseActivity
import com.restart.jetpack_compose_examples.Main.FirstComposable
import com.restart.jetpack_compose_examples.Main.MainActivity
import com.restart.jetpack_compose_examples.Main.MainRepository
import com.restart.jetpack_compose_examples.Main.MainViewModel
import com.restart.jetpack_compose_examples.Main.SecoundComposable
import com.restart.jetpack_compose_examples.profile.ProfileActivity
import com.restart.jetpack_compose_examples.profile.ProfileComposable
import com.restart.jetpack_compose_examples.profile.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.core.annotation.KoinReflectAPI
import org.koin.dsl.module

@OptIn(KoinReflectAPI::class)
val appModule = module {
    single { MainRepository() }


    scope<MainActivity> {
        scoped { MainViewModel(get()) }
        scoped { CoroutineScope(Dispatchers.Default + Job()).apply {  } }
        scoped { FirstComposable(get(), get()) }
        scoped { SecoundComposable(get()) }
    }

    scope<ProfileActivity> {
        scoped { ProfileViewModel(get()) }
        scoped { ProfileComposable(get()) }
    }
}
