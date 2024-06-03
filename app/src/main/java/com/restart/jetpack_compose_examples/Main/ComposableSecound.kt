package com.restart.jetpack_compose_examples.Main

import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.restart.jetpack_compose_examples.profile.ProfileActivity
import org.koin.android.ext.android.get

@Composable
fun ComposableSecound(navController: NavHostController) {

    val context = LocalContext.current
    val composable: SecoundComposable = (context as MainActivity).get()
    composable.text = "Hello, World!"

    Button(onClick = {
        context.apply {
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }
    }) {
        Text(text = "Navigate to First Screen")
    }

}