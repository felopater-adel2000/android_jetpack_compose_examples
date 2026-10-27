package com.restart.jetpack_compose_examples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.felo.ui_sdk.UISDKRoot
import com.restart.jetpack_compose_examples.databinding.ActivityMainBinding
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme

class MainActivity : ComponentActivity() {

    val url =
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfv-1HbVT7Rk0SXLexFsTjNHijDRYldwxALeQJp-CKezjOpzhvIY-kaEs&s=10"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.composeView.setContent {

        }
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack_compose_examplesTheme {

                UISDKRoot(
                    modifier = Modifier.fillMaxSize(),
                    //resourcesDrawable = com.felo.ui_sdk.R.drawable.ic_clock,
                )

            }
        }
    }
}


