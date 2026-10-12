package com.restart.jetpack_compose_examples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.restart.jetpack_compose_examples.databinding.ActivityMainBinding
import com.restart.jetpack_compose_examples.ui.theme.Jetpack_compose_examplesTheme

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.composeView.setContent {

        }
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack_compose_examplesTheme {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "Hello World!",
                        fontSize = 25.sp,
                        color = androidx.compose.ui.graphics.Color.Cyan
                    )

                }

            }
        }
    }
}


