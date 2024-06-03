package com.restart.jetpack_compose_examples.profile

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.restart.jetpack_compose_examples.Main.MainActivity

@Composable
fun ComposableProfile() {

    val context = LocalContext.current

    val composable : ProfileComposable = (context as ProfileActivity).scope.get()

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Button(
            onClick = {
                      context.apply {
                          startActivity(Intent(this, MainActivity::class.java))
                      }
            },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = "Back")
        }
    }

}